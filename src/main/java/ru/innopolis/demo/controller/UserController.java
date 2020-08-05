package ru.innopolis.demo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.demo.domain.UserAccount;
import ru.innopolis.demo.domain.UserType;
import ru.innopolis.demo.service.UserService;

/**
 * UserController
 *
 * @author Stanislav_Klevtsov
 */
@Controller
@RequestMapping("/users")
@Log4j2
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/{userAccountId}")
    public String showUser(Model model, @PathVariable Long userAccountId) {
        model.addAttribute("user_account", userService.getUserById(userAccountId));
        return "user_account";
    }

    @GetMapping("/add/")
    public String addUser() {
        return "/add_user";
    }

    @PostMapping("/add/")
    public String saveUser(Model model,
                           @RequestParam String userType,
                           @RequestParam String userName,
                           @RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String deliveryAddress,
                           @RequestParam String password) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword  = passwordEncoder.encode(password);

        UserAccount newUser = new UserAccount();
        newUser.setUserType(userType);
        newUser.setUserName(userName);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setDeliveryAddress(deliveryAddress);
        newUser.setPassword(encodedPassword);

        userService.saveNewUser(newUser);
        return showAllUsers(model);
    }

    @GetMapping("/update/{userAccountId}")
    public String updateUser(Model model, @PathVariable Long userAccountId) {
        model.addAttribute("user_account", userService.getUserById(userAccountId));
        model.addAttribute("user_type", UserType.values());
        return "update_user";
    }

    @PostMapping("/update/{userAccountId}")
    public String changeUser(Model model,
                             @PathVariable Long userAccountId,
                             @RequestParam String userType,
                             @RequestParam String userName,
                             @RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String deliveryAddress,
                             @RequestParam String password) {

        model.addAttribute("user_account", userService.getUserById(userAccountId));

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword  = passwordEncoder.encode(password);

        UserAccount updatedUser = new UserAccount();
        updatedUser.setUserType(userType);
        updatedUser.setUserName(userName);
        updatedUser.setFirstName(firstName);
        updatedUser.setLastName(lastName);
        updatedUser.setDeliveryAddress(deliveryAddress);
        updatedUser.setPassword(encodedPassword);

        userService.changeUserById(userAccountId, updatedUser);
        return showAllUsers(model);
    }

    @GetMapping("/delete/{userAccountId}")
    public String deleteUser(Model model, @PathVariable Long userAccountId) {
        userService.deleteUserById(userAccountId);
        return showAllUsers(model);
    }
}