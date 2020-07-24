package ru.innopolis.demo.controller;

import liquibase.pro.packaged.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.demo.service.ShopService;

@Controller
@RequestMapping("/shop")
public class ShopUserController{

    private ShopService shopService;

    @Autowired
    public ShopUserController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/all")
    public String getAllShop(Model model){
        model.addAttribute("shops",shopService.getAllShops());
        return "shops";
    }

    @GetMapping("/{shopID}")
    public String getShopByID(Model model, @PathVariable long shopID){
        model.addAttribute("oneShop", shopService.findShopByShopID(shopID));
        return "oneShop";
    }

}
