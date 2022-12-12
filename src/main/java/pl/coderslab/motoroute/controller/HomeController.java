package pl.coderslab.motoroute.controller;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.mail.MailException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.motoroute.dto.UserCreateDto;
import pl.coderslab.motoroute.dto.UserForgotPassDto;
import pl.coderslab.motoroute.dto.UserResetPassDto;
import pl.coderslab.motoroute.entity.Route;
import pl.coderslab.motoroute.entity.User;
import pl.coderslab.motoroute.security.CurrentUser;
import pl.coderslab.motoroute.service.RouteService;
import pl.coderslab.motoroute.service.UserService;

import javax.servlet.http.HttpServletRequest;
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

    /* ================= STARTER ================= */
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

    /* ================= PASSWORD RESET ================= */
    @GetMapping("/forgot-password")
    public String forgotPasswordForm(Model model) {
        model.addAttribute("userForgotPassDto", new UserForgotPassDto());
        return "userForgotPasswordForm";

    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@Valid UserForgotPassDto userForgotPassDto, BindingResult result, HttpServletRequest request, Model model) {
        if (result.hasErrors()) {
            return "userForgotPasswordForm";
        }
        String email = userForgotPassDto.getEmail();
        String token = RandomString.make(30);
        String url = request.getRequestURL().toString();
        String link = url.replace(request.getServletPath(), "");
        String resetPassLink = link + "/reset-password?token=" + token;
        try {
            userService.updateResetPasswordTokenByUserEmail(token, email);
            userService.sendResetPassLinkViaEmail(userForgotPassDto.getEmail(), resetPassLink);
        } catch (MailException mailException) {
            String failMsg = "Nie udało się wysłać maila na adres " + email + ". Spróbuj ponownie.";
            model.addAttribute("resultMsg", failMsg);
            return "my-message";
        }
        String successMsg = "Sukces! Link do zresetowania hasła wysłano na: " + email;
        model.addAttribute("resultMsg", successMsg);
        return "my-message";
    }

    @GetMapping("/reset-password")
    public String resetPasswordForm(@RequestParam(name = "token") String token, Model model) {
        User user = userService.getUserByResetPasswordToken(token);
        if (user == null) {
            model.addAttribute("resultMsg", "Nie udało się zresetować hasła. Niepoprawny lub nieaktualny link");
            return "my-message";
        }
        model.addAttribute("userResetPassDto", new UserResetPassDto());
        return "userResetPasswordForm";

    }

    @PostMapping("/reset-password")
    public String resetPassword(@Valid UserResetPassDto userResetPassDto, BindingResult result, @RequestParam(name = "token") String token, Model model) {
        if (result.hasErrors()) {
            return "userResetPasswordForm";
        }
        User user = userService.getUserByResetPasswordToken(token);
        String message;
        if (user == null) {
            message = "Nie udało się zresetować hasła. Niepoprawny lub nieaktualny link";
        } else {
            userService.setNewPassword(user, userResetPassDto.getNewPassword());
            message = "Sukces! Twoje hasło zostało zresetowane. Zaloguj się używając nowego hasła";
        }
        model.addAttribute("resultMsg", message);
        return "my-message";
    }

    /* ================= ADDITIONAL VIEWS AND TESTS ================= */
    @RequestMapping("/app/maintenance")
    public String showMaintenance() {
        return "app-maintenance";
    }

    @GetMapping("/current")
    @ResponseBody
    public String admin(@AuthenticationPrincipal CurrentUser customUser) {
        User entityUser = customUser.getUser();
        return "Hello " + entityUser.getUsername();
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String url = request.getRequestURL().toString();
        String link = url.replace(request.getServletPath(), "");
        return "Context path: " + contextPath + " URL: " + url + " Link: " + link;
    }

}
