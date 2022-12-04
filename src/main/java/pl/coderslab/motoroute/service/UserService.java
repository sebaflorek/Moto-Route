package pl.coderslab.motoroute.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.motoroute.dto.UserCreateDto;
import pl.coderslab.motoroute.dto.UserEditDto;
import pl.coderslab.motoroute.dto.UserPassDto;
import pl.coderslab.motoroute.entity.Role;
import pl.coderslab.motoroute.entity.Route;
import pl.coderslab.motoroute.entity.User;
import pl.coderslab.motoroute.repository.RoleRepository;
import pl.coderslab.motoroute.repository.TripRepository;
import pl.coderslab.motoroute.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TripRepository tripRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void save(User user) {
        userRepository.save(user);
    }

    public void saveAsUserWithDto(UserCreateDto userCreateDto) {
        User user = new User();
        user.setUsername(userCreateDto.getUsername());
        user.setEmail(userCreateDto.getEmail());
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        user.setEnabled(1);
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        userRepository.save(user);
    }

    public void saveAsAdminWithDto(UserCreateDto userCreateDto) {
        User user = new User();
        user.setUsername(userCreateDto.getUsername());
        user.setEmail(userCreateDto.getEmail());
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        user.setEnabled(1);
        Role userRole = roleRepository.findByName("ROLE_USER");
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole, adminRole)));
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByUserEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User findById(long id) {
        User user = userRepository.findById(id).orElse(null);
        addRoutesToUser(user);
        return user;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void editUserById(long id, UserEditDto userEditDto) {
        User user = userRepository.findById(id).orElse(null);
        assert user != null;
        user.setUsername(userEditDto.getUsername());
        user.setEmail(userEditDto.getEmail());
        userRepository.save(user);
    }

    public void updatePassword(UserPassDto userPassDto) {
        User user = userRepository.findById(userPassDto.getId()).orElse(null);
        assert user != null;
        user.setPassword(passwordEncoder.encode(userPassDto.getNewPassword()));
        userRepository.save(user);
    }

    public void fullDeleteUserById(long id) {
        tripRepository.deleteAllByUserId(id); //TYMCZASOWO
        userRepository.deleteUserAllFavoriteRoutesByUserId(id); //TYMCZASOWO +
        userRepository.deleteUserRolesByUserId(id); //TYMCZASOWO +
        userRepository.deleteById(id);
    }

    public void addFavRouteToUser(long userId, Route route) {
        User user = findById(userId);
        user.getFavouriteRoutes().add(route);
        userRepository.save(user);
    }

    public void delFavRouteFromUser(long userId, Route route) {
        User user = findById(userId);
        user.getFavouriteRoutes().remove(route);
        userRepository.save(user);
    }

    private void addRoutesToUser(User user) {
        Hibernate.initialize(user.getFavouriteRoutes());
    }


}
