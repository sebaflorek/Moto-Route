package pl.coderslab.motoroute.dto;

import lombok.Data;
import pl.coderslab.motoroute.entity.Route;
import pl.coderslab.motoroute.entity.Trip;
import pl.coderslab.motoroute.validation.UniqueDayNumber;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@UniqueDayNumber
public class TripDayCreateDto {

    @Min(1)
    private String dayNumber;

    @NotNull(message = "{invalid.route.route-notnull}")
    private Route route;

    @NotNull(message = "{invalid.trip.trip-notnull}")
    private Trip trip;

}
