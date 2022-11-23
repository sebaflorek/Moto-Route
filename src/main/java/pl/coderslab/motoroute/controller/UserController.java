package pl.coderslab.motoroute.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.motoroute.entity.User;
import pl.coderslab.motoroute.security.CurrentUser;
import pl.coderslab.motoroute.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /* METODY TYMCZASOWE - START */
    @GetMapping("/createUser")
    public void createUser() {
        User user = new User();
        user.setUsername("user");
        user.setEmail("user@mr.pl");
        user.setPassword("qwerty");
        userService.save(user);
    }

    @GetMapping("/createAdmin")
    public void createAdmin() {
        User user = new User();
        user.setUsername("admin");
        user.setEmail("admin@mr.pl");
        user.setPassword("asdfgh");
        userService.saveAsAdmin(user);
    }

    @GetMapping("/current")
    @ResponseBody
    public String admin(@AuthenticationPrincipal CurrentUser customUser) {
        User entityUser = customUser.getUser();
        return "Hello " + entityUser.getUsername();
    }
    /* METODY TYMCZASOWE - STOP */

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "userForm";
    }

    @PostMapping("/register")
    public String registerForm(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "userForm";
        }
        userService.save(user);
        return "userForm";
    }


}
