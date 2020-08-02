package ru.innopolis.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.demo.domain.UserAccount;
import ru.innopolis.demo.repos.UserRepository;
import ru.innopolis.demo.service.UserService;

/**
 * RegisterController
 *
 * @author Stanislav_Klevtsov
 */
@Controller
public class RegisterController {

    private final UserService userService;
    private UserRepository userRepository;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register.html")
    public String addUser() {
        return "/register";
    }

    @PostMapping("/register")
    public String doRegister(Model model,
                             @RequestParam String userType,
                             @RequestParam String userName,
                             @RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String deliveryAddress,
                             @RequestParam String password) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword  = passwordEncoder.encode(password);

        UserAccount user = new UserAccount();
        user.setUserType(userType);
        user.setUserName(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDeliveryAddress(deliveryAddress);
        user.setPassword(encodedPassword);

        userService.saveNewUser(user);

        return "login";
    }
}