package pl.coderslab.motoroute.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import pl.coderslab.motoroute.dto.TripDayCreateDto;
import pl.coderslab.motoroute.entity.Trip;
import pl.coderslab.motoroute.entity.TripDay;
import pl.coderslab.motoroute.repository.TripDayRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TripDayService {
    private final TripDayRepository tripDayRepository;

    public void save(TripDay tripDay) {
        tripDayRepository.save(tripDay);
    }

    public void createWithDto(TripDayCreateDto tripDayCreateDto) {
        TripDay tripDay = new TripDay();
        tripDay.setDayNumber(Integer.parseInt(tripDayCreateDto.getDayNumber()));
        tripDay.setTrip(tripDayCreateDto.getTrip());
        tripDay.setRoute(tripDayCreateDto.getRoute());
        tripDayRepository.save(tripDay);
    }

    public TripDay findById(long id) {
        return tripDayRepository.findById(id).orElse(null);
    }

    public boolean isTripDayUniqueByItsDayNumberAndTripId(int dayNumber, long tripId) {
        List<TripDay> tripDaysByDayNumberAndTripId = tripDayRepository.findTripDaysByDayNumberAndTripId(dayNumber, tripId);
        return tripDaysByDayNumberAndTripId.isEmpty();
    }

    public void deleteById(long id) {
        tripDayRepository.deleteById(id);
    }

    public void deleteByTripDay(TripDay tripDay) {
        tripDayRepository.delete(tripDay);
    }

    public void deleteAllByTrip(Trip trip) {
        tripDayRepository.deleteAllByTrip(trip);
    }

    private void addRouteToTripDay(TripDay tripDay) {
        Hibernate.initialize(tripDay.getRoute());
    }

    private void addTripToTripDay(TripDay tripDay) {
        Hibernate.initialize(tripDay.getTrip());
    }



}
