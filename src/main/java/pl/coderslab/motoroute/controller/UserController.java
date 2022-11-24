package pl.coderslab.motoroute.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.motoroute.service.UserService;

@Controller
@RequestMapping("/app/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


}
