package pl.coderslab.motoroute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.motoroute.entity.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

}
