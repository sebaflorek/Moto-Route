package pl.coderslab.motoroute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.motoroute.entity.Trip;
import pl.coderslab.motoroute.entity.User;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findTripsByUser(User user);
    Trip findFirstTripByUserOrderByCreatedDesc(User user);
    int countAllByUser(User user);

    @Modifying
    @Transactional
    void deleteAllByUserId(Long userId);

}
