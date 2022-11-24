package pl.coderslab.motoroute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.motoroute.entity.Route;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findRoutesByAuthorId(Long authorId);

}
