package pl.coderslab.motoroute.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.motoroute.dto.RouteCreateDto;
import pl.coderslab.motoroute.dto.RouteSendDto;
import pl.coderslab.motoroute.entity.*;
import pl.coderslab.motoroute.security.CurrentUser;
import pl.coderslab.motoroute.service.*;

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
    private final TripService tripService;
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

    /* ================= ROUTES READING ================= */
    @RequestMapping("/dashboard")
    public String showPulpit(Model model) {
        User user = currentUser.getUser();
        Route latestRoute = routeService.findLatestByAuthorId(user.getId());
        int routesNum = routeService.countAllByAuthorId(user.getId());
        Trip latestTrip = tripService.findLatestByUser(user);
        int tripsNum = tripService.countAllByUser(user);
        model.addAttribute("latestRoute", latestRoute);
        model.addAttribute("latestTrip", latestTrip);
        model.addAttribute("routesNum", routesNum);
        model.addAttribute("tripsNum", tripsNum);
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
        routeService.createWithDto(routeCreateDto);
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
            routeService.deleteRoutesFromUsersFavorites(id);
            routeService.deleteById(id);
            return "redirect:/app/route/my-list";
        }
        return "error-illegal";
    }

    @RequestMapping("/{id}/fav-add")
    public String addRouteToFavorite(@PathVariable Long id) {
        Route route = routeService.findById(id);
        userService.addFavRouteToUser(currentUser.getUser().getId(), route);
        routeService.routeLikePlusOne(id);
        return "redirect:/app/route/fav-list";
    }

    @RequestMapping("/{id}/fav-del")
    public String delRouteFromFavorite(@PathVariable Long id) {
        Route route = routeService.findById(id);
        userService.delFavRouteFromUser(currentUser.getUser().getId(), route);
        routeService.routeLikeMinusOne(id);
        return "redirect:/app/route/fav-list";
    }

    /* ================= EMAILS ================= */
    @RequestMapping("/download/{id}")
    public String downloadRoute(Model model, @PathVariable Long id) {
        String email = currentUser.getUser().getEmail();
        String receiverName = currentUser.getUser().getUsername();
        Route route = routeService.findById(id);
        return sendEmail(email, receiverName, route, model);
    }

    @GetMapping("/send/{id}")
    public String sendRouteForm(Model model, @PathVariable String id) {
        model.addAttribute("routeSendDto", new RouteSendDto());
        return "app-routeSend";
    }

    @PostMapping("/send/{id}")
    public String sendRoute(@Valid RouteSendDto routeSendDto, BindingResult result, Model model, @PathVariable Long id) {
        String email = routeSendDto.getEmail();
        String receiverName = routeSendDto.getName();
        Route route = routeService.findById(id);
        if (result.hasErrors()) {
            return "app-routeSend";
        }
        return sendEmail(email, receiverName, route, model);
    }

    /* ================= ADDITIONAL VIEWS ================= */
    @RequestMapping("/info")
    public String getMapLinkInfo() {
        return "app-routeInstruction";
    }

    /* ================= ADDITIONAL METHODS ================= */
    private String sendEmail(String email, String receiverName, Route route, Model model) {
        try {
            routeService.sendRouteViaEmail(email, receiverName, route);
            routeService.routePopularityPlusOne(route.getId());
        } catch (MailException mailException) {
            String failMsg = "Nie udało się wysłać maila na adres " + email + ". Spróbuj ponownie.";
            model.addAttribute("resultMsg", failMsg);
            return "my-message";
        }
        String successMsg = "Sukces! Szczegóły wybranej trasy wysłano na adres: " + email;
        model.addAttribute("resultMsg", successMsg);
        return "my-message";
    }


}
