package pl.coderslab.motoroute.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.motoroute.entity.Route;
import pl.coderslab.motoroute.entity.Trip;
import pl.coderslab.motoroute.entity.TripDay;
import pl.coderslab.motoroute.security.CurrentUser;
import pl.coderslab.motoroute.service.RouteService;
import pl.coderslab.motoroute.service.TripDayService;
import pl.coderslab.motoroute.service.TripService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/app/trip-day")
@RequiredArgsConstructor
public class TripDayController {
    private final TripDayService tripDayService;
    private final TripService tripService;
    private final RouteService routeService;
    private CurrentUser currentUser;

    /* ================= MODEL ATTRIBUTES ================= */
    @ModelAttribute("currentUser")
    public CurrentUser getCurrentUser(@AuthenticationPrincipal CurrentUser currentUser) {
        this.currentUser = currentUser;
        return currentUser;
    }

    @ModelAttribute("routeList")
    public List<Route> getRoutes() {
        return routeService.findAll();
    }

    @ModelAttribute("userTripList")
    public List<Trip> getTripsByUser() {
        return tripService.findAllByUser(currentUser.getUser());
    }

    /* ================= TRIP DAY MANAGEMENT ================= */
    @GetMapping("/add")
    public String tripDayForm(Model model) {
        model.addAttribute("tripDay", new TripDay());
        return "app-tripDayForm";
    }

    @PostMapping("/add")
    public String saveTripDay(@Valid TripDay tripDay, BindingResult result) {
        if (result.hasErrors()) {
            return "app-tripDayForm";
        }
        long tripId = tripDay.getTrip().getId();
        tripDayService.save(tripDay);
        return "redirect:/app/trip/details/" + tripId;
    }

    @RequestMapping("/delete/{tripDayId}/{tripId}")
    public String deleteDayFromTrip(@PathVariable Long tripDayId, @PathVariable Long tripId) {
        TripDay tripDay = tripDayService.findById(tripDayId);
        Trip trip = tripService.findById(tripId);
        if (trip.getUser().getId() == currentUser.getUser().getId()) {
            tripDayService.deleteByTripDay(tripDay);
            return "redirect:/app/trip/details/" + tripId;
        }
        return "error-illegal";
    }

    @RequestMapping("/delete-all/{tripId}")
    public String deleteAllDaysFromTrip(@PathVariable Long tripId) {
        Trip trip = tripService.findById(tripId);
        if (trip.getUser().getId() == currentUser.getUser().getId()) {
            tripDayService.deleteAllByTrip(trip);
            return "redirect:/app/trip/details/" + tripId;
        }
        return "error-illegal";

    }


}
