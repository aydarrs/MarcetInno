package ru.innopolis.demo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.demo.domain.Courier;
import ru.innopolis.demo.domain.DeliveryMethod;
import ru.innopolis.demo.domain.UserAccount;
import ru.innopolis.demo.domain.UserType;
import ru.innopolis.demo.service.CourierService;
import ru.innopolis.demo.service.OrderService;
import ru.innopolis.demo.service.UserService;

import java.util.Optional;

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
    private CourierService courierService;
    private OrderService orderService;

    @Autowired
    public UserController(UserService userService, CourierService courierService, OrderService orderService) {
        this.userService = userService;
        this.courierService = courierService;
        this.orderService = orderService;
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
    public String addUser(Model model) {
        model.addAttribute("user_type", UserType.values());
        return "/add_user";
    }

    @PostMapping("/add/")
    public String saveUser(Model model,
                           @RequestParam String userType,
                           @RequestParam String userName,
                           @RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String password) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword  = passwordEncoder.encode(password);

        UserAccount newUser = new UserAccount();
        newUser.setUserType(userType);
        newUser.setUserName(userName);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setPassword(encodedPassword);

        userService.saveNewUser(newUser);
        return "redirect:/" + "users/all";
    }

    @GetMapping("/update/{userAccountId}")
    public String updateUser(Model model, @PathVariable Long userAccountId) {
        model.addAttribute("user_account", userService.getUserById(userAccountId));
        return "update_user";
    }

    @PostMapping("/update/{userAccountId}")
    public String changeUser(Model model,
                             @PathVariable Long userAccountId,
                             @RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String password) {

        UserAccount user = userService.getUserById(userAccountId);
        model.addAttribute("user_account", user);

        UserAccount updatedUser = new UserAccount();
        updatedUser.setUserType(user.getUserType());
        updatedUser.setUserName(user.getUserName());
        updatedUser.setFirstName(firstName);
        updatedUser.setLastName(lastName);

        if (!password.isEmpty()) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword  = passwordEncoder.encode(password);
            updatedUser.setPassword(encodedPassword);
        } else {
            updatedUser.setPassword(user.getPassword());
        }

        userService.changeUserById(userAccountId, updatedUser);

        return "redirect:/users/all";
    }

    @PostMapping("/update/{userAccountId}/cabinet")
    public String changeUserFromCabinet(Model model,
                             @PathVariable Long userAccountId,
                             @RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String password,
                             @RequestParam(required=false) String deliveryMethod) {

        UserAccount user = userService.getUserById(userAccountId);
        model.addAttribute("user_account", user);

        UserAccount updatedUser = new UserAccount();
        updatedUser.setUserType(user.getUserType());
        updatedUser.setUserName(user.getUserName());
        updatedUser.setFirstName(firstName);
        updatedUser.setLastName(lastName);

        userService.changeUserById(userAccountId, updatedUser);

        if (user.getUserType().equals(UserType.ADMIN.getRole())) {
            return "redirect:/admin/index.html?success";
        }
        if (user.getUserType().equals(UserType.COURIER.getRole())) {

            if (deliveryMethod != null) {
                Courier courier = courierService.getCourierByUser(user);
                courier.setDeliveryMethod(DeliveryMethod.valueOf(deliveryMethod));
                courierService.saveChanged(courier);
            }

            return  "redirect:/courier/index.html?success";
        }
        if(user.getUserType().equals(UserType.SELLER.getRole())) {
            return "redirect:/seller/index.html?success";
        }
        return "redirect:/customer/index.html?success";
    }

    @GetMapping("/delete/{userAccountId}")
    public String deleteUser(Model model, @PathVariable Long userAccountId) {
        UserAccount user = userService.getUserById(userAccountId);

        if(orderService.getOrdersByUserAccount(userService.getUserById(userAccountId)) != null) {
            orderService.deleteOrderByUserID(userAccountId);
        }
        else if(courierService.getCourierByUser(user) != null) {
            courierService.deleteCourier(user);
        }
        userService.deleteUserById(userAccountId);
        return "redirect:/" + "users/all";
    }
}