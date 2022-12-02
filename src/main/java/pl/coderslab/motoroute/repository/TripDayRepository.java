package pl.coderslab.motoroute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.motoroute.entity.Trip;
import pl.coderslab.motoroute.entity.TripDay;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TripDayRepository extends JpaRepository<TripDay, Long> {

    @Query("select td from TripDay td where td.dayNumber = :dayNum and td.trip.id = :tripId")
    List<TripDay> findTripDaysByDayNumberAndTripId(@Param("dayNum") int dayNumber, @Param("tripId") long tripId);

    @Modifying
    @Transactional
    void deleteAllByTrip(Trip trip);





}
