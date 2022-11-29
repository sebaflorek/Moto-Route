package pl.coderslab.motoroute.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.motoroute.dto.TripCreateDto;
import pl.coderslab.motoroute.entity.Trip;
import pl.coderslab.motoroute.security.CurrentUser;
import pl.coderslab.motoroute.service.RouteService;
import pl.coderslab.motoroute.service.TripService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/app/trip")
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;
    private final RouteService routeService;
    private CurrentUser currentUser;

    /* ================= MODEL ATTRIBUTES ================= */
    @ModelAttribute("currentUser")
    public CurrentUser getCurrentUser(@AuthenticationPrincipal CurrentUser currentUser) {
        this.currentUser = currentUser;
        return currentUser;
    }

    /* ================= TRIPS READING ================= */
    @GetMapping("/list")
    public String getMyTripsList(Model model) {
        List<Trip> trips = tripService.findAllByUser(currentUser.getUser());
        model.addAttribute("tripList", trips);
        return "app-tripList";
    }

    @GetMapping("/details/{id}")
    public String tripDetails(Model model, @PathVariable Long id) {
        Trip trip = tripService.findById(id);
        if (trip.getUser().getId() == currentUser.getUser().getId()) {
            model.addAttribute("trip", tripService.findById(id));
            return "app-tripDetails";
        }
        return "error-illegal";
    }

    /* ================= TRIPS MANAGEMENT ================= */
    @GetMapping("/add")
    public String tripForm(Model model) {
        model.addAttribute("tripCreateDto", new TripCreateDto());
        return "app-tripAdd";
    }

    @PostMapping("/add")
    public String addTrip(@Valid TripCreateDto tripCreateDto, BindingResult result) {
        if (result.hasErrors()) {
            return "app-tripAdd";
        }
        tripService.createWithDto(tripCreateDto, currentUser.getUser());
        return "redirect:/app/route/dashboard";
    }

    @GetMapping("/edit/{id}")
    public String editTrip(Model model, @PathVariable Long id) {
        Trip trip = tripService.findById(id);
        if (trip.getUser().getId() == currentUser.getUser().getId()) {
            model.addAttribute("trip", trip);
            return "app-tripEdit";
        }
        return "error-illegal";
    }

    @PostMapping("/edit/{id}")
    public String updateTrip(@Valid Trip trip, BindingResult result) {
        if (result.hasErrors()) {
            return "app-tripEdit";
        }
        if (trip.getUser().getId() == currentUser.getUser().getId()) {
            tripService.save(trip);
            return "redirect:/app/trip/list";
        }
        return "error-illegal";
    }

    @RequestMapping("/delete/{id}")
    public String deleteTrip(@PathVariable Long id) {
        Trip trip = tripService.findById(id);
        if (trip.getUser().getId() == currentUser.getUser().getId()) {
            tripService.deleteById(id);
            return "redirect:/app/trip/list";
        }
        return "error-illegal";
    }

}
