package pl.coderslab.motoroute.controller;


import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.motoroute.dto.RouteDto;
import pl.coderslab.motoroute.entity.Region;
import pl.coderslab.motoroute.entity.Route;
import pl.coderslab.motoroute.entity.Type;
import pl.coderslab.motoroute.entity.User;
import pl.coderslab.motoroute.security.CurrentUser;
import pl.coderslab.motoroute.service.RegionService;
import pl.coderslab.motoroute.service.RouteService;
import pl.coderslab.motoroute.service.TypeService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/app/route")
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;
    private final RegionService regionService;
    private final TypeService typeService;
    private CurrentUser currentUser;

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

    @RequestMapping("/dashboard") // tymczasowo
    public String showPulpit() {
        return "dashboard";
    }

    @RequestMapping("/info")
    public String getMapLinkInfo() {
        return "routeInstruction";
    }

    @GetMapping("/my-list")
    public String getMyRoutesList(Model model) {
        List<Route> routes = routeService.findAllByAuthorId(currentUser.getUser().getId());
        model.addAttribute("routeList", routes);
        return "routeMyList";
    }

    @GetMapping("/fav-list")
    public String getMyFavRoutesList(Model model) {
        List<Route> favouriteRoutes = currentUser.getUser().getFavouriteRoutes();
        model.addAttribute("routeList", favouriteRoutes);
        return "routeFavList";
    }

    @GetMapping("/add")
    public String routeForm(Model model) {
        model.addAttribute("routeDto", new RouteDto());
        return "routeForm";
    }

    @PostMapping("/add")
    public String addRoute(@Valid RouteDto routeDto, BindingResult result) {
        if (result.hasErrors()) {
            return "routeForm";
        }
        routeService.saveWithDto(routeDto);
        return "redirect:/app/route/owner-list";
    }
}
