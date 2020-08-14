package ru.innopolis.demo.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.demo.domain.Shop;
import ru.innopolis.demo.domain.UserAccount;
import ru.innopolis.demo.domain.UserType;
import ru.innopolis.demo.service.*;

import java.util.*;

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
        List<UserAccount> users = getFreeSellers();
        model.addAttribute("users", users);
        return "/add_shop";
    }



    @PostMapping("/add/")
    public String saveShop(Model model,
                           @RequestParam String shopAddress,
                           @RequestParam String shopName,
                           @RequestParam Long userId,
                           @RequestParam MultipartFile file) {

        Shop newShop = new Shop();
        newShop.setAddress(shopAddress);
        newShop.setName(shopName);

        if (userId != null) {
            newShop.setUserId(userService.getUserById(userId));
        }
        String image = shopService.copyImage(file, newShop.getShopID());
        newShop.setImage(image);

        shopService.saveNewShop(newShop);
        return "redirect:/" + "shops/all";
    }

    @GetMapping("/update/{shopId}")
    public String updateShop(Model model, @PathVariable Long shopId) {
        // Здесь мы формируем список владельцев не назначенных на магазины
        // ПОлучаем списки занятых магазинов и владельцев
        List<UserAccount> users = getFreeSellers();

        model.addAttribute("users", users);
        model.addAttribute("shop", shopService.getShopById(shopId));
        return "update_shop";
    }

    @PostMapping("/update/{shopId}")
    public String changeShop(Model model,
                             @PathVariable Long shopId,
                             @RequestParam String shopAddress,
                             @RequestParam String shopName,
                             @RequestParam Long userId,
                             @RequestParam MultipartFile file) {

        model.addAttribute("shop", shopService.getShopById(shopId));

        Shop updatedShop = new Shop();
        updatedShop.setAddress(shopAddress);
        updatedShop.setName(shopName);

        if (userId != null) {
            updatedShop.setUserId(userService.getUserById(userId));
        }


        String image = shopService.copyImage(file, shopId);
        updatedShop.setImage(image);
        System.out.println(updatedShop.getImage());

        shopService.changeShopById(shopId, updatedShop);
        return "redirect:/" + "shops/all";
    }

    /*@GetMapping("/delete/{shopId}")
    public String deleteShop(Model model, @PathVariable Long shopId) {
        return "redirect:/" + "shops/all";
    }*/

    private List<UserAccount> getFreeSellers() {
        // Здесь мы формируем список владельцев не назначенных на магазины
        // ПОлучаем списки занятых магазинов и владельцев
        List<UserAccount> users = (ArrayList<UserAccount>) userService.getAllUsersByUserType(UserType.SELLER.getRole());
        List<Shop> shops = (ArrayList<Shop>) shopService.getBusyShops();
        Set<Long> userIds = new HashSet<>();
        for (Shop shop : shops) {
            userIds.add(shop.getUserId().getUserId());
        }
        // Удаляем занятых владельцев из списка для передачи на View
        Iterator iterator = users.iterator();
        while (iterator.hasNext()) {
            UserAccount user = (UserAccount) iterator.next();
            Long userId = user.getUserId();
            if (userIds.contains(userId)) {
                iterator.remove();
            }
        }
        return users;
    }

}
