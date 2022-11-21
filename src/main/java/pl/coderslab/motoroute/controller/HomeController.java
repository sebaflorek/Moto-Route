package pl.coderslab.motoroute.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/about")
    public String showAbout() {
        return "about";
    }

    @RequestMapping("/contact")
    public String showContact() {
        return "contact";
    }

    @GetMapping("/nie") // TEST
    @ResponseBody
    public String noAuthorization() {
        return "BEZ AUTORYZACJI";
    }

    @GetMapping("/tak") // TEST
    @ResponseBody
    public String withAuthorization() {
        return "DLA ZALOGOWANYCH";
    }


}
