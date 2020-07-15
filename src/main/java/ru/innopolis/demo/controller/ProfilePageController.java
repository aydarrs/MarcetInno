package ru.innopolis.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.demo.domain.ShopUser;

@Controller
@RequestMapping("/profile")
public class ProfilePageController {

    private ShopUser user;

     {
        user = new ShopUser();
        user.setUserName("cobratms");
        user.setFirstName("Nail");
        user.setLastName("Zinnurov");
        user.setAge(22);
    }

    @GetMapping("/user")
    public String helloWorldController(Model model) {
        model.addAttribute("user", user);
        return "user";
    }

}