package ru.innopolis.demo.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.demo.domain.Shop;
import ru.innopolis.demo.domain.UserAccount;
import ru.innopolis.demo.service.ShopService;
import ru.innopolis.demo.service.ShopServiceManager;

@Controller
@RequestMapping("/shops")
@Log4j2
public class ShopController {

    private ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
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
    public String addShop() {
        return "/add_shop";
    }

    @PostMapping("/add/")
    public String saveUser(Model model,
                           @RequestParam String shopAddress,
                           @RequestParam String shopName) {

        Shop newShop = new Shop();
        newShop.setAddress(shopAddress);
        newShop.setName(shopName);

        shopService.saveNewShop(newShop);
        return showAllShops(model);
    }

    @GetMapping("/update/{shopId}")
    public String updateShop(Model model, @PathVariable Long shopId) {
        model.addAttribute("shop", shopService.getShopById(shopId));
        return "update_shop";
    }

    @PostMapping("/update/{shopId}")
    public String changeShop(Model model,
                             @PathVariable Long shopId,
                             @RequestParam String shopAddress,
                             @RequestParam String shopName) {

        model.addAttribute("shop", shopService.getShopById(shopId));

        Shop updatedShop = new Shop();
        updatedShop.setAddress(shopAddress);
        updatedShop.setName(shopName);

        shopService.changeShopById(shopId, updatedShop);
        return showAllShops(model);
    }

    @GetMapping("/delete/{shopId}")
    public String deleteShop(Model model, @PathVariable Long shopId) {
        shopService.deleteShopById(shopId);
        return showAllShops(model);
    }

}
