package pl.coderslab.motoroute.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.motoroute.dto.RouteCreateDto;
import pl.coderslab.motoroute.entity.Route;
import pl.coderslab.motoroute.repository.RouteRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;

    public void save(Route route) {
        routeRepository.save(route);
    }

    public void saveWithDto(RouteCreateDto routeCreateDto) {
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

    public int countAllByAuthorId(long authorId) {
        return routeRepository.countAllByAuthorId(authorId);
    }

    public void deleteRoutesFromUsersFavorites(long routeId) {
        routeRepository.deleteRoutesFromUsersFavorites(routeId);
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



}
