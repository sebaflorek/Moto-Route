package pl.coderslab.motoroute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.motoroute.entity.User;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findUserByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "delete from users_roles where user_id = :userId", nativeQuery = true)
    void deleteUserRolesByUserId(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value = "delete from users_routes where user_id = :userId", nativeQuery = true)
    void deleteUserAllFavoriteRoutesByUserId(@Param("userId") Long userId);




}
