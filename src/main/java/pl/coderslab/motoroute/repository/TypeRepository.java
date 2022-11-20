package pl.coderslab.motoroute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.motoroute.entity.Type;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TypeRepository extends JpaRepository<Type, Long> {

}
