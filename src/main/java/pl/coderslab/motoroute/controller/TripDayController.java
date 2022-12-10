package pl.coderslab.motoroute.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.motoroute.dto.TripDayCreateDto;
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

    /* ================= MODEL ATTRIBUTES ================= */
    @ModelAttribute("currentUser")
    public CurrentUser getCurrentUser(@AuthenticationPrincipal CurrentUser currentUser) {
        return currentUser;
    }

    @ModelAttribute("routeList")
    public List<Route> getRoutes() {
        return routeService.findAll();
    }

    @ModelAttribute("userTripList")
    public List<Trip> getTripsByUser(@AuthenticationPrincipal CurrentUser currentUser) {
        return tripService.findAllByUser(currentUser.getUser());
    }

    /* ================= TRIP DAY MANAGEMENT ================= */
//    @GetMapping("/add")
//    public String tripDayForm(Model model) {
//        model.addAttribute("tripDay", new TripDay());
//        return "app-tripDayForm";
//    }

    @GetMapping("/add")
    public String tripDayForm(Model model) {
        model.addAttribute("tripDay", new TripDayCreateDto());
        return "app-tripDayForm";
    }

//    @PostMapping("/add")
//    public String saveTripDay(@Valid TripDay tripDay, BindingResult result) {
//        if (result.hasErrors()) {
//            return "app-tripDayForm";
//        }
//        long tripId = tripDay.getTrip().getId();
//        tripDayService.save(tripDay);
//        return "redirect:/app/trip/details/" + tripId;
//    }

    @PostMapping("/add")
    public String saveTripDay(@ModelAttribute("tripDay") @Valid TripDayCreateDto tripDay, BindingResult result) {
        if (result.hasErrors()) {
            return "app-tripDayForm";
        }
        long tripId = tripDay.getTrip().getId();
        tripDayService.createWithDto(tripDay);
        return "redirect:/app/trip/details/" + tripId;
    }

    @RequestMapping("/delete/{tripDayId}/{tripId}")
    public String deleteDayFromTrip(@PathVariable Long tripDayId, @PathVariable Long tripId, @AuthenticationPrincipal CurrentUser currentUser) {
        TripDay tripDay = tripDayService.findById(tripDayId);
        Trip trip = tripService.findById(tripId);
        if (trip.getUser().getId() == currentUser.getUser().getId()) {
            tripDayService.deleteByTripDay(tripDay);
            return "redirect:/app/trip/details/" + tripId;
        }
        return "error-illegal";
    }

    @RequestMapping("/delete-all/{tripId}")
    public String deleteAllDaysFromTrip(@PathVariable Long tripId, @AuthenticationPrincipal CurrentUser currentUser) {
        Trip trip = tripService.findById(tripId);
        if (trip.getUser().getId() == currentUser.getUser().getId()) {
            tripDayService.deleteAllByTrip(trip);
            return "redirect:/app/trip/details/" + tripId;
        }
        return "error-illegal";

    }
    /* ================= TESTS ================= */
    @RequestMapping("/test/{dayNumber}/{tripId}")
    @ResponseBody
    public String test(@PathVariable int dayNumber, @PathVariable long tripId) {
        boolean check = tripDayService.isTripDayUniqueByItsDayNumberAndTripId(dayNumber, tripId);
        return "test: " + check;
    }


}
