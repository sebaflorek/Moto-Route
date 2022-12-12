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
import pl.coderslab.motoroute.service.TripService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/app/trip")
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;

    /* ================= MODEL ATTRIBUTES ================= */
    @ModelAttribute("currentUser")
    public CurrentUser getCurrentUser(@AuthenticationPrincipal CurrentUser currentUser) {
        return currentUser;
    }

    /* ================= TRIPS READ ================= */
    @GetMapping("/list")
    public String getMyTripsList(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        List<Trip> trips = tripService.findAllByUser(currentUser.getUser());
        model.addAttribute("tripList", trips);
        return "app-tripList";
    }

    @GetMapping("/details/{id}")
    public String tripDetails(Model model, @PathVariable Long id, @AuthenticationPrincipal CurrentUser currentUser) {
        Trip trip = tripService.findById(id);
        //trip.getTripDays().sort(Comparator.comparingInt(TripDay::getDayNumber));
        if (trip.getUser().getId() == currentUser.getUser().getId()) {
            model.addAttribute("trip", trip);
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
    public String addTrip(@Valid TripCreateDto tripCreateDto, BindingResult result, @AuthenticationPrincipal CurrentUser currentUser) {
        if (result.hasErrors()) {
            return "app-tripAdd";
        }
        tripService.createWithDto(tripCreateDto, currentUser.getUser());
        return "redirect:/app/trip/list";
    }

    @GetMapping("/edit/{id}")
    public String editTrip(Model model, @PathVariable Long id, @AuthenticationPrincipal CurrentUser currentUser) {
        Trip trip = tripService.findById(id);
        if (trip.getUser().getId() == currentUser.getUser().getId()) {
            model.addAttribute("trip", trip);
            return "app-tripEdit";
        }
        return "error-illegal";
    }

    @PostMapping("/edit/{id}")
    public String updateTrip(@Valid Trip trip, BindingResult result, @AuthenticationPrincipal CurrentUser currentUser) {
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
    public String deleteTrip(@PathVariable Long id, @AuthenticationPrincipal CurrentUser currentUser) {
        Trip trip = tripService.findById(id);
        if (trip.getUser().getId() == currentUser.getUser().getId()) {
            tripService.deleteById(id);
            return "redirect:/app/trip/list";
        }
        return "error-illegal";
    }

}
