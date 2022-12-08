package pl.coderslab.motoroute.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.motoroute.dto.UserEditDto;
import pl.coderslab.motoroute.dto.UserPassDto;
import pl.coderslab.motoroute.dto.UserReadDto;
import pl.coderslab.motoroute.entity.User;
import pl.coderslab.motoroute.mapper.UserMapper;
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
    private final UserMapper userMapper;

    @ModelAttribute("currentUser")
    public CurrentUser getCurrentUser(@AuthenticationPrincipal CurrentUser currentUser) {
        return currentUser;
    }

    /* ================= ROUTES READING ================= */
    @GetMapping("/details")
    public String getUserDetails(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        User user = userService.findById(currentUser.getUser().getId());
        UserReadDto userReadDto = new UserReadDto(user.getUsername(), user.getEmail());
        model.addAttribute("userInfo", userReadDto);
        return "app-userRead";
    }

    /* ================= USERS MANAGEMENT ================= */
//    @GetMapping("/edit") // NO MAPPER
//    public String editUserForm(Model model) {
//        User user = userService.findById(currentUser.getUser().getId());
//        UserEditDto userEditDto = new UserEditDto();
//        userEditDto.setEmail(user.getEmail());
//        userEditDto.setUsername(user.getUsername());
//        userEditDto.setId(user.getId());
//        model.addAttribute("userEditDto", userEditDto);
//        return "app-userEdit";
//    }

    @GetMapping("/edit") // WITH MAPPER
    public String editUserForm(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        User user = userService.findById(currentUser.getUser().getId());
        UserEditDto userEditDto = userMapper.userToUserEditDto(user);
        model.addAttribute("userEditDto", userEditDto);
        return "app-userEdit";
    }

    @PostMapping("/edit")
    public String editUser(@Valid UserEditDto userEditDto, BindingResult result) {
        if (result.hasErrors()) {
            return "app-userEdit";
        }
        userService.updateUserById(userEditDto);
        return "redirect:/app/user/details";
    }

    @GetMapping("/change-pass")
    public String changePassForm(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        UserPassDto userPassDto = new UserPassDto();
        userPassDto.setId(currentUser.getUser().getId());
        model.addAttribute("userPassDto", userPassDto);
        return "app-userPassEdit";
    }

    @PostMapping("/change-pass")
    public String changePassword(@Valid UserPassDto userPassDto, BindingResult result, HttpServletRequest request, Model model) {
        if (result.hasErrors()) {
            return "app-userPassEdit";
        }
        userService.updatePassword(userPassDto);
        try {
            request.logout();
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
        String resultMsg = "Hasło zostało zmienione. Zaloguj się ponownie używając nowego hasła.";
        model.addAttribute("resultMsg", resultMsg);
        return "my-message";
    }

    @RequestMapping("/delete")
    public String deleteUser(Model model, HttpServletRequest request, @AuthenticationPrincipal CurrentUser currentUser) {
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
