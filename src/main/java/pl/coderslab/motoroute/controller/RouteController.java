package pl.coderslab.motoroute.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.motoroute.dto.RouteDto;
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
        model.addAttribute("routeDto", new RouteDto());
        return "app-routeForm";
    }

    @PostMapping("/add")
    public String addRoute(@Valid RouteDto routeDto, BindingResult result) {
        if (result.hasErrors()) {
            return "app-routeForm";
        }
        routeService.saveWithDto(routeDto);
        return "redirect:/app/route/owner-list";
    }

    /* ================= ADDITIONAL VIEWS ================= */
    @RequestMapping("/info")
    public String getMapLinkInfo() {
        return "app-routeInstruction";
    }


}
