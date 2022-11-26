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
import pl.coderslab.motoroute.dto.UserCreateDto;
import pl.coderslab.motoroute.entity.Route;
import pl.coderslab.motoroute.entity.User;
import pl.coderslab.motoroute.security.CurrentUser;
import pl.coderslab.motoroute.service.RouteService;
import pl.coderslab.motoroute.service.UserService;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;
    private final RouteService routeService;
    private final Validator validator;

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("userCreateDto", new UserCreateDto());
        return "userRegister";
    }

    @PostMapping("/register")
    public String registerForm(@Valid UserCreateDto userCreateDto, BindingResult result, Model model) {
        Set<ConstraintViolation<UserCreateDto>> violations = validator.validate(userCreateDto);
        if (!violations.isEmpty()) { // temporary
            for (ConstraintViolation<UserCreateDto> violation : violations) {
                System.out.println(violation.getPropertyPath() + "<-->" + violation.getMessage());
            }
        }
        if (result.hasErrors()) {
            model.addAttribute("errorList", violations);
            return "userRegister";
        }
        userService.saveAsUserWithDto(userCreateDto);
        model.addAttribute("registeredUser", userCreateDto.getUsername());
        return "welcomePage";
    }

    @RequestMapping("/about")
    public String showAbout() {
        return "about";
    }

    @GetMapping("/routes")
    public String getAllRoutes(Model model) {
        List<Route> routes = routeService.findAll();
        model.addAttribute("routeList", routes);
        return "routes";
    }

    @RequestMapping("/contact")
    public String showContact() {
        return "contact";
    }

    /* STARTER */
    @GetMapping("/starter/createUser")
    public void createUser() {
        UserCreateDto userDto = new UserCreateDto();
        userDto.setUsername("user");
        userDto.setEmail("user@mr.pl");
        userDto.setPassword("user");
        userDto.setMatchingPassword("user");
        userService.saveAsUserWithDto(userDto);
    }

    @GetMapping("/starter/createAdmin")
    public void createAdmin() {
        UserCreateDto userDto = new UserCreateDto();
        userDto.setUsername("admin");
        userDto.setEmail("admin@mr.pl");
        userDto.setPassword("admin");
        userDto.setMatchingPassword("admin");
        userService.saveAsAdminWithDto(userDto);
    }

    @GetMapping("/current")
    @ResponseBody
    public String admin(@AuthenticationPrincipal CurrentUser customUser) {
        User entityUser = customUser.getUser();
        return "Hello " + entityUser.getUsername();
    }

    /* ================= ADDITIONAL VIEWS ================= */
    @RequestMapping("/app/maintenance")
    public String showMaintenance() {
        return "app-maintenance";
    }

}
