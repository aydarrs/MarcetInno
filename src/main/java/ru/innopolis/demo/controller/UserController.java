package ru.innopolis.demo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.demo.domain.UserAccount;
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

    @GetMapping("/add/{userAccount}")
    public String saveUser(Model model, @PathVariable UserAccount userAccount) {
        userService.saveNewUser(userAccount);
        return showAllUsers(model);
    }

    @GetMapping("/update/{userAccountId}/{userAccount}")
    public String changeUser(Model model, @PathVariable Long userAccountId, @PathVariable UserAccount userAccount) {
        userService.changeUserById(userAccountId, userAccount);
        return showAllUsers(model);
    }

    @GetMapping("/delete/{userAccountId}")
    public String deleteUser(Model model, @PathVariable Long userAccountId) {
        userService.deleteUserById(userAccountId);
        return showAllUsers(model);
    }
}