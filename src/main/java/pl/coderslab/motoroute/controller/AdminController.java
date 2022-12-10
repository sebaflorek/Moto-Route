package pl.coderslab.motoroute.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.motoroute.dto.UserDetailsDto;
import pl.coderslab.motoroute.dto.UserEditDto;
import pl.coderslab.motoroute.entity.User;
import pl.coderslab.motoroute.mapper.RouteMapper;
import pl.coderslab.motoroute.mapper.UserMapper;
import pl.coderslab.motoroute.service.RouteService;
import pl.coderslab.motoroute.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final RouteService routeService;
    private final UserMapper userMapper;
    private final RouteMapper routeMapper;

    /* ================= USER MANAGEMENT ================= */
    @RequestMapping("/user/list")
    public String getUserList(Model model) {
        List<UserDetailsDto> users = userService.findAll()
                .stream()
                .map(userMapper::userToUserDetailsDto)
                .collect(Collectors.toList());
        model.addAttribute("userList", users);
        return "admin-userList";
    }

    @GetMapping("/user/edit/{id}")
    public String editUserForm(Model model, @PathVariable Long id) {
        User user = userService.findById(id);
        UserEditDto userEditDto = userMapper.userToUserEditDto(user);
        model.addAttribute("userEditDto", userEditDto);
        return "app-userEdit";
    }

    @PostMapping("/user/edit/{id}")
    public String editUser(@Valid UserEditDto userEditDto, BindingResult result) {
        if (result.hasErrors()) {
            return "app-userEdit";
        }
        userService.updateUserByUserEditDto(userEditDto);
        return "redirect:/admin/user/list";
    }

    @GetMapping("/user/disable/{id}")
    public String disableUser(@PathVariable Long id) {
        userService.disableUserById(id);
        return "redirect:/admin/user/list";
    }

    @GetMapping("/user/enable/{id}")
    public String enableUser(@PathVariable Long id) {
        userService.enableUserById(id);
        return "redirect:/admin/user/list";
    }

    @RequestMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        // public String deleteUser(@PathVariable Long id, HttpServletRequest request, HttpSecurity http, SessionRegistry sessionRegistry) {
        userService.fullDeleteUserById(id);
        return "redirect:/admin/user/list";
    }

    /* ================= TRIP MANAGEMENT ================= */
    @RequestMapping("/route/list")
    public String getRouteList(Model model) {
        model.addAttribute("routeList", routeService.findAll());
        return "admin-routeList";
    }

    @RequestMapping("/route/delete/{id}")
    public String deleteRoute(@PathVariable Long id, Model model) {
        routeService.conditionalDeleteRouteById(id);
        if (routeService.isRouteUsed(id)) {
            String resultMsg = "Nie można usunąć wybranej trasy. Trasa przypisana jest do wycieczki.";
            model.addAttribute("resultMsg", resultMsg);
            return "my-message";
        }
        return "redirect:/admin/route/list";
    }


}
