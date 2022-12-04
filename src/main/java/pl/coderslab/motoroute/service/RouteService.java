package pl.coderslab.motoroute.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.motoroute.dto.RouteCreateDto;
import pl.coderslab.motoroute.emails.EmailService;
import pl.coderslab.motoroute.entity.Route;
import pl.coderslab.motoroute.repository.RouteRepository;
import pl.coderslab.motoroute.repository.TripDayRepository;

import javax.swing.*;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private final TripDayRepository tripDayRepository;
    private final EmailService emailService;

    public void save(Route route) {
        routeRepository.save(route);
    }

    public void createWithDto(RouteCreateDto routeCreateDto) {
        Route route = new Route();
        route.setName(routeCreateDto.getName());
        route.setDistance(routeCreateDto.getDistance());
        route.setDescription(routeCreateDto.getDescription());
        route.setRegion(routeCreateDto.getRegion());
        route.setType(routeCreateDto.getType());
        route.setMap(routeCreateDto.getMap());
        route.setPopularity(0);
        route.setLikes(0);
        route.setAuthorId(routeCreateDto.getAuthorId());
        routeRepository.save(route);
    }

    public Route findById(long id) {
        return routeRepository.findById(id).orElse(null);
    }

    public Route findLatestByAuthorId(long authorId) {
        return routeRepository.findFirstRouteByAuthorIdOrderByCreatedDesc(authorId);
    }

    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    public List<Route> findAllByAuthorId(long authorId) {
        return routeRepository.findRoutesByAuthorId(authorId);
    }

    public void update(Route route) {
        routeRepository.save(route);
    }

    public void deleteById(long id) {
        routeRepository.deleteById(id);
    }

    public void deleteRoutesFromUsersFavorites(long routeId) {
        routeRepository.deleteRoutesFromUsersFavorites(routeId);
    }

    public void conditionalDeleteRouteById(long id) {
        if (!isRouteUsed(id)) {
            routeRepository.deleteRoutesFromUsersFavorites(id);
            routeRepository.deleteById(id);
        }
    }

    public int countAllByAuthorId(long authorId) {
        return routeRepository.countAllByAuthorId(authorId);
    }

    public void routeLikePlusOne(long routeId) {
        Route route = findById(routeId);
        route.setLikes(route.getLikes() + 1);
        routeRepository.save(route);
    }

    public void routeLikeMinusOne(long routeId) {
        Route route = findById(routeId);
        route.setLikes(route.getLikes() - 1);
        routeRepository.save(route);
    }

    public void routePopularityPlusOne(long routeId) {
        Route route = findById(routeId);
        route.setPopularity(route.getPopularity() + 1);
        routeRepository.save(route);
    }

    public void sendRouteViaEmail(String email, String receiverName, Route route) {
        String title = "Moto Route: Trasa";
        StringBuilder message = new StringBuilder();
        message
                .append("Cześć " + receiverName.toUpperCase() + ",\n")
                .append("\nDziękujemy za skorzystanie z serwisu Moto Route. Poniżej szczegóły wybranej trasy.\n")
                .append("\nTrasa: " + route.getName().toUpperCase() + "\n")
                .append("Lokalizacja: " + route.getRegion().getName() + "\n")
                .append("Typ: " + route.getType().getName() + "\n")
                .append("Długość: " + route.getDistance() + "km\n")
                .append("Mapa: " + route.getMap() + "\n")
                .append("\nPozdrawiamy,\n")
                .append("Zespół Moto Route");

        emailService.sendSimpleEmail(email, title, message.toString());

    }

    public boolean isRouteUsed(long routeId) {
        return !tripDayRepository.findTripDaysByRouteId(routeId).isEmpty();
    }

}
