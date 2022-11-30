package pl.coderslab.motoroute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import pl.coderslab.motoroute.entity.Trip;
import pl.coderslab.motoroute.entity.TripDay;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TripDayRepository extends JpaRepository<TripDay, Long> {

    @Modifying
    @Transactional
    void deleteAllByTrip(Trip trip);

}
