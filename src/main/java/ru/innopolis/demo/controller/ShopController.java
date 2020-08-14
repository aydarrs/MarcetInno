package ru.innopolis.demo.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.demo.domain.Shop;
import ru.innopolis.demo.domain.UserAccount;
import ru.innopolis.demo.domain.UserType;
import ru.innopolis.demo.service.*;

@Controller
@RequestMapping("/shops")
@Log4j2
public class ShopController {

    private ShopService shopService;
    private UserService userService;

    @Autowired
    public ShopController(ShopService shopService, UserService userService) {
        this.shopService = shopService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public String showAllShops(Model model) {
        model.addAttribute("shops", shopService.getAllShops());
        return "shops";
    }

    @GetMapping("/{shopId}")
    public String showShopByShopID(Model model, @PathVariable Long shopId) {
        model.addAttribute("shop", shopService.getShopById(shopId));
        return "oneShop";
    }

    @GetMapping("/add/")
    public String addShop(Model model) {
        model.addAttribute("users", userService.getAllUsersByUserType(UserType.SELLER.getRole()));
        return "/add_shop";
    }

    @PostMapping("/add/")
    public String saveShop(Model model,
                           @RequestParam String shopAddress,
                           @RequestParam String shopName,
                           @RequestParam Long userId) {

        Shop newShop = new Shop();
        newShop.setAddress(shopAddress);
        newShop.setName(shopName);

        if (userId != null) {
            newShop.setUserId(userService.getUserById(userId));
        }

        shopService.saveNewShop(newShop);
        return "redirect:/" + "shops/all";
    }

    @GetMapping("/update/{shopId}")
    public String updateShop(Model model, @PathVariable Long shopId) {
        model.addAttribute("users", userService.getAllUsersByUserType(UserType.SELLER.getRole()));
        model.addAttribute("shop", shopService.getShopById(shopId));
        return "update_shop";
    }

    @PostMapping("/update/{shopId}")
    public String changeShop(Model model,
                             @PathVariable Long shopId,
                             @RequestParam String shopAddress,
                             @RequestParam String shopName,
                             @RequestParam Long userId) {

        model.addAttribute("shop", shopService.getShopById(shopId));

        Shop updatedShop = new Shop();
        updatedShop.setAddress(shopAddress);
        updatedShop.setName(shopName);

        if (userId != null) {
            updatedShop.setUserId(userService.getUserById(userId));
        }

        shopService.changeShopById(shopId, updatedShop);
        return "redirect:/" + "shops/all";
    }

    /*@GetMapping("/delete/{shopId}")
    public String deleteShop(Model model, @PathVariable Long shopId) {
        return "redirect:/" + "shops/all";
    }*/

}
