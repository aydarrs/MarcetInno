package ru.innopolis.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.demo.pojo.User;

@Controller
@RequestMapping("/profile")
public class ProfilePageController {

    private static final User user;

    static {
        user = new User("cobratms", "Nail", "Zinnurov", 22);
    }

    @GetMapping("/user")
    public String helloWorldController(Model model) {
        model.addAttribute("user", user);
        return "user";
    }

}