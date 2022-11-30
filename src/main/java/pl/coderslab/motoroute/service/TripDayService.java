package pl.coderslab.motoroute.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import pl.coderslab.motoroute.entity.Trip;
import pl.coderslab.motoroute.entity.TripDay;
import pl.coderslab.motoroute.repository.TripDayRepository;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TripDayService {
    private final TripDayRepository tripDayRepository;

    public void save(TripDay tripDay) {
        tripDayRepository.save(tripDay);
    }

    public TripDay findById(long id) {
        TripDay tripDay = tripDayRepository.findById(id).orElse(null);
        addRouteToTripDay(tripDay);
        return tripDay;
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


}
