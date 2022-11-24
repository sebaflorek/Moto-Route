package pl.coderslab.motoroute.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.motoroute.dto.RouteDto;
import pl.coderslab.motoroute.entity.Route;
import pl.coderslab.motoroute.repository.RouteRepository;
import pl.coderslab.motoroute.security.CurrentUser;

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

    public void saveWithDto(RouteDto routeDto) {
        Route route = new Route();
        route.setName(routeDto.getName());
        route.setDistance(routeDto.getDistance());
        route.setDescription(routeDto.getDescription());
        route.setRegion(routeDto.getRegion());
        route.setType(routeDto.getType());
        route.setMap(routeDto.getMap());
        route.setPopularity(0);
        route.setLikes(0);
        route.setAuthorId(routeDto.getAuthorId());
        routeRepository.save(route);
    }

    public Route findById(long id) {
        return routeRepository.findById(id).orElse(null);
    }

    public List<Route> findAll() {
        return routeRepository.findAll();
    }
    public List<Route> findAllByAuthorId(long authorId) {
        return routeRepository.findRoutesByAuthorId(authorId);
    }


//    public void update(Route route) {
//        routeRepository.save(route);
//    }

    public void deleteById(long id) {
        routeRepository.deleteById(id);
    }


}
