package pl.coderslab.motoroute.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import pl.coderslab.motoroute.dto.TripCreateDto;
import pl.coderslab.motoroute.entity.Trip;
import pl.coderslab.motoroute.entity.User;
import pl.coderslab.motoroute.repository.TripRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TripService {
    private final TripRepository tripRepository;

    public void save(Trip trip) {
        tripRepository.save(trip);
    }

    public void createWithDto(TripCreateDto tripCreateDto, User user) {
        Trip trip = new Trip();
        trip.setName(tripCreateDto.getName());
        trip.setDescription(tripCreateDto.getDescription());
        trip.setUser(user);
        tripRepository.save(trip);
    }

    public Trip findById(long id) {
        Trip trip = tripRepository.findById(id).orElse(null);
        addTripDaysToTrip(trip);
        return trip;
    }

    public Trip findLatestByUser(User user) {
        Trip latestTripByUser = tripRepository.findFirstTripByUserOrderByCreatedDesc(user);
        addTripDaysToTrip(latestTripByUser);
        return latestTripByUser;
    }

    public List<Trip> findAll() {
        return tripRepository.findAll();
    }

    public List<Trip> findAllByUser(User user) {
        List<Trip> tripsByUser = tripRepository.findTripsByUser(user);
        tripsByUser.forEach(this::addTripDaysToTrip);
        return tripsByUser;
    }

    public void deleteById(long id) {
        tripRepository.deleteById(id);
    }

    public void deleteByTrip(Trip trip) {
        tripRepository.delete(trip);
    }

    public int countAllByUser(User user) {
        return tripRepository.countAllByUser(user);
    }

    private void addTripDaysToTrip(Trip trip) {
        Hibernate.initialize(trip.getTripDays());
    }
}
