package pl.coderslab.motoroute.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.motoroute.dto.UserEditDto;
import pl.coderslab.motoroute.dto.UserReadDto;
import pl.coderslab.motoroute.entity.User;
import pl.coderslab.motoroute.security.CurrentUser;
import pl.coderslab.motoroute.service.TripService;
import pl.coderslab.motoroute.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/app/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final TripService tripService;
    private CurrentUser currentUser;

    @ModelAttribute("currentUser")
    public CurrentUser getCurrentUser(@AuthenticationPrincipal CurrentUser currentUser) {
        this.currentUser = currentUser;
        return currentUser;
    }

    /* ================= ROUTES READING ================= */
    @GetMapping("/details")
    public String getUserDetails(Model model) {
        User user = userService.findById(currentUser.getUser().getId());
        UserReadDto userReadDto = new UserReadDto(user.getUsername(), user.getEmail());
        model.addAttribute("userInfo", userReadDto);
        return "app-userRead";
    }

    /* ================= USERS MANAGEMENT ================= */
    @GetMapping("/edit")
    public String editUserForm(Model model) {
        User user = userService.findById(currentUser.getUser().getId());
        UserEditDto userEditDto = new UserEditDto();
        userEditDto.setEmail(user.getEmail());
        userEditDto.setUsername(user.getUsername());
        userEditDto.setId(user.getId());
        model.addAttribute("userEditDto", userEditDto);
        return "app-userEdit";
    }

    @PostMapping("/edit")
    public String editUser(@Valid UserEditDto userEditDto, BindingResult result) {
        if (result.hasErrors()) {
            return "app-userEdit";
        }
        userService.editUserById(currentUser.getUser().getId(), userEditDto);
        return "redirect:/app/user/details";
    }

    @RequestMapping("/delete")
    public String deleteUser(Model model, HttpServletRequest request) {
//        tripService.deleteAllTripsByUserId(currentUser.getUser().getId()); // TYMCZASOWO
//        userService.purgeUserDataById(currentUser.getUser().getId()); // TYMCZASOWO
        userService.fullDeleteUserById(currentUser.getUser().getId());
        try {
            request.logout();
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
        String successMsg = "Zostałeś wylogowany, a Twoje konto zostało usunięte!";
        model.addAttribute("resultMsg", successMsg);
        return "my-message";
    }


}
