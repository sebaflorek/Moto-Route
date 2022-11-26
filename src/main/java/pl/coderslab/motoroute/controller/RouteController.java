package pl.coderslab.motoroute.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.motoroute.dto.RouteCreateDto;
import pl.coderslab.motoroute.entity.*;
import pl.coderslab.motoroute.security.CurrentUser;
import pl.coderslab.motoroute.service.RegionService;
import pl.coderslab.motoroute.service.RouteService;
import pl.coderslab.motoroute.service.TypeService;
import pl.coderslab.motoroute.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/app/route")
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;
    private final RegionService regionService;
    private final TypeService typeService;
    private final UserService userService;
    private CurrentUser currentUser;

    /* ================= MODEL ATTRIBUTES ================= */
    @ModelAttribute("regionList")
    public List<Region> getRegions() {
        return regionService.findAll();
    }

    @ModelAttribute("typeList")
    public List<Type> getTypes() {
        return typeService.findAll();
    }

    @ModelAttribute("currentUser")
    public CurrentUser getCurrentUser(@AuthenticationPrincipal CurrentUser currentUser) {
        this.currentUser = currentUser;
        return currentUser;
    }

    /* ================= ROUTES VIEWING ================= */
    @RequestMapping("/dashboard")
    public String showPulpit(Model model) {
        long currentUserId = currentUser.getUser().getId();
        Route latestRoute = routeService.findLatestByAuthorId(currentUserId);
        int routesNum = routeService.countAllByAuthorId(currentUserId);
        model.addAttribute("latestRoute", latestRoute);
        model.addAttribute("latestTrip", null);
        model.addAttribute("routesNum", routesNum);
        model.addAttribute("tripsNum", null);
        return "app-dashboard";
    }

    @GetMapping("/my-list")
    public String getMyRoutesList(Model model) {
        List<Route> routes = routeService.findAllByAuthorId(currentUser.getUser().getId());
        model.addAttribute("routeList", routes);
        return "app-routeMyList";
    }

    @GetMapping("/fav-list")
    public String getMyFavRoutesList(Model model) {
        List<Route> favouriteRoutes = userService.findById(currentUser.getUser().getId()).getFavouriteRoutes();
        model.addAttribute("routeList", favouriteRoutes);
        return "app-routeFavList";
    }

    @GetMapping("/details/{id}")
    public String routeDetails(Model model, @PathVariable Long id) {
        model.addAttribute("route", routeService.findById(id));
        return "app-routeDetails";
    }

    /* ================= ROUTES MANAGEMENT ================= */
    @GetMapping("/add")
    public String routeForm(Model model) {
        model.addAttribute("routeCreateDto", new RouteCreateDto());
        return "app-routeAdd";
    }

    @PostMapping("/add")
    public String addRoute(@Valid RouteCreateDto routeCreateDto, BindingResult result) {
        if (result.hasErrors()) {
            return "app-routeAdd";
        }
        routeService.saveWithDto(routeCreateDto);
        return "redirect:/app/route/dashboard";
    }

    @GetMapping("/edit/{id}")
    public String editRoute(Model model, @PathVariable Long id) {
        Route route = routeService.findById(id);
        if (route.getAuthorId() == currentUser.getUser().getId()) {
            model.addAttribute("route", route);
            return "app-routeEdit";
        }
        return "error-illegal";
    }

    @PostMapping("/edit/{id}")
    public String updateRoute(@Valid Route route, BindingResult result) {
        if (result.hasErrors()) {
            return "app-routeEdit";
        }
        if (route.getAuthorId() == currentUser.getUser().getId()) {
            routeService.save(route);
            return "redirect:/app/route/my-list";
        }
        return "error-illegal";
    }

    @RequestMapping("/delete/{id}")
    public String deleteRoute(@PathVariable Long id) {
        Route route = routeService.findById(id);
        if (route.getAuthorId() == currentUser.getUser().getId()) {
            routeService.deleteById(id);
            return "redirect:/app/route/my-list";
        }
        return "error-illegal";
    }

    @RequestMapping("/{id}/fav-add")
    public String addRouteToFavorite(@PathVariable Long id) {
        Route route = routeService.findById(id);
        userService.addFavRouteToUser(currentUser.getUser().getId(), route);
        return "redirect:/app/route/fav-list";
    }

    @RequestMapping("/{id}/fav-del")
    public String delRouteFromFavorite(@PathVariable Long id) {

        return "redirect:/app/route/fav-list";
    }

    /* ================= ADDITIONAL VIEWS ================= */
    @RequestMapping("/info")
    public String getMapLinkInfo() {
        return "app-routeInstruction";
    }


}
