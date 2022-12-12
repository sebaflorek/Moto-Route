package pl.coderslab.motoroute.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import pl.coderslab.motoroute.dto.RouteAdminReadDto;
import pl.coderslab.motoroute.dto.RouteCreateDto;
import pl.coderslab.motoroute.dto.RouteEditDto;
import pl.coderslab.motoroute.entity.Route;

@Mapper(componentModel = "spring")
//@Mapper(componentModel = "spring", uses = {RouteMapperResolver.class})
public interface RouteMapper {
    RouteEditDto routeToRouteEditDto(Route route);
    Route routeEditDtoToRoute(RouteEditDto routeEditDto);
    Route routeCreateDtoToRoute(RouteCreateDto routeCreateDto);
    void routeEditDtoToRouteEntity(@MappingTarget Route route, RouteEditDto routeEditDto);
    @Mapping(source = "created", target = "created", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "updated", target = "updated", dateFormat = "yyyy-MM-dd HH:mm:ss")
    RouteAdminReadDto routeToRouteAdminReadDto(Route route);


}
