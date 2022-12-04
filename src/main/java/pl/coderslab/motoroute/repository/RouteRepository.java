package pl.coderslab.motoroute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.motoroute.entity.Route;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findRoutesByAuthorId(Long authorId);
    Route findFirstRouteByAuthorIdOrderByCreatedDesc(Long authorId);
    int countAllByAuthorId(Long authorId);

    @Modifying
    @Transactional
    @Query(value = "delete from users_routes where route_id = :routeId", nativeQuery = true)
    void deleteRoutesFromUsersFavorites(@Param("routeId") Long routeId);

}
