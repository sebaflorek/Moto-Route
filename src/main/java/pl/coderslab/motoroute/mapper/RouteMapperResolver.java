package pl.coderslab.motoroute.mapper;

import pl.coderslab.motoroute.dto.RouteCreateDto;
import pl.coderslab.motoroute.dto.RouteEditDto;
import pl.coderslab.motoroute.entity.Route;
import pl.coderslab.motoroute.service.RouteService;

// @Component
public class RouteMapperResolver /* implements RouteMapper */ {

    private final RouteService routeService;

    public RouteMapperResolver(RouteService routeService) {
        this.routeService = routeService;
    }

    //@Override
    public RouteEditDto routeToRouteEditDto(Route route) {
        if (route == null) {
            return null;
        }

        RouteEditDto routeEditDto = new RouteEditDto();

        routeEditDto.setId(route.getId());
        routeEditDto.setName(route.getName());
        routeEditDto.setDistance(String.valueOf(route.getDistance()));
        routeEditDto.setMap(route.getMap());
        routeEditDto.setDescription(route.getDescription());
        routeEditDto.setRegion(route.getRegion());
        routeEditDto.setType(route.getType());
        routeEditDto.setAuthorId(route.getAuthorId());

        return routeEditDto;
    }

    //@Override
    public Route routeEditDtoToRoute(RouteEditDto routeEditDto) {
        if (routeEditDto == null) {
            return null;
        }

        Route route = routeService.findById(routeEditDto.getId());

        route.setName(routeEditDto.getName());
        if (routeEditDto.getDistance() != null) {
            route.setDistance(Integer.parseInt(routeEditDto.getDistance()));
        }
        route.setMap(routeEditDto.getMap());
        route.setDescription(routeEditDto.getDescription());
        route.setRegion(routeEditDto.getRegion());
        route.setType(routeEditDto.getType());

        return route;
    }

    //@Override
    public Route routeCreateDtoToRoute(RouteCreateDto routeCreateDto) {
        if (routeCreateDto == null) {
            return null;
        }

        Route route = new Route();

        route.setName(routeCreateDto.getName());
        if (routeCreateDto.getDistance() != null) {
            route.setDistance(Integer.parseInt(routeCreateDto.getDistance()));
        }
        route.setMap(routeCreateDto.getMap());
        route.setDescription(routeCreateDto.getDescription());
        route.setRegion(routeCreateDto.getRegion());
        route.setType(routeCreateDto.getType());
        route.setAuthorId(routeCreateDto.getAuthorId());

        return route;
    }
}
