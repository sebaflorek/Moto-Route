package pl.coderslab.motoroute.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.coderslab.motoroute.dto.TripDayCreateDto;
import pl.coderslab.motoroute.entity.TripDay;
import pl.coderslab.motoroute.service.TripDayService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueDayValidator implements ConstraintValidator<UniqueDayNumber, TripDayCreateDto> {
    private final TripDayService tripDayService;

    @Override
    public void initialize(UniqueDayNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(TripDayCreateDto tripDay, ConstraintValidatorContext constraintValidatorContext) {
        if (tripDay.getTrip() == null) {
            return true;
        }
        long tripId = tripDay.getTrip().getId();
        int dayNumber = tripDay.getDayNumber();
        if (!tripDayService.isTripDayUniqueByItsDayNumberAndTripId(dayNumber, tripId)) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("{invalid.dayNumber.dayNumber-unique}")
                    .addPropertyNode("dayNumber")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
