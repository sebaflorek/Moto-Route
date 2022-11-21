package pl.coderslab.motoroute.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.motoroute.entity.Region;
import pl.coderslab.motoroute.entity.Route;
import pl.coderslab.motoroute.entity.Type;
import pl.coderslab.motoroute.service.RegionService;
import pl.coderslab.motoroute.service.RouteService;
import pl.coderslab.motoroute.service.TypeService;

import java.util.List;

@Controller
@RequestMapping("/route")
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;
    private final RegionService regionService;
    private final TypeService typeService;

    @ModelAttribute("regionList")
    public List<Region> getRegions() {
        return regionService.findAll();
    }

    @ModelAttribute("typeList")
    public List<Type> getTypes() {
        return typeService.findAll();
    }

    @GetMapping("/all")
    public String getAllRoutes(Model model) {
        List<Route> routes = routeService.findAll();
        model.addAttribute("routeList", routes);
        return "routeAll";
    }

    @RequestMapping("/dashboard") // tymczasowo
    public String showPulpit() {
        return "dashboard";
    }
}
