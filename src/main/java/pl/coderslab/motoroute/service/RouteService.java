package pl.coderslab.motoroute.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.motoroute.entity.Route;
import pl.coderslab.motoroute.repository.RouteRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;

    public void add(Route route) {
        routeRepository.save(route);
    }

    public Route findById(long id) {
        return routeRepository.findById(id).orElse(null);
    }

    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    public void update(Route route) {
        routeRepository.save(route);
    }

    public void deleteById(long id) {
        routeRepository.deleteById(id);
    }


}
