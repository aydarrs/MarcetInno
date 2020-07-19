package ru.innopolis.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.demo.service.ShopUserService;


/**
 * ShopUserController
 *
 * @author Dmitrii_Blazhko 18-Jul-20
 */

@Controller
@RequestMapping("/shop/")
public class ShopUserController {

    private ShopUserService shopUserService;

    @Autowired
    public ShopUserController(ShopUserService shopUserService) {
        this.shopUserService = shopUserService;
    }

    @GetMapping("all")
    public String showAllShop(Model model){
        model.addAttribute("all",shopUserService.getAllShops());
        return "shop";
    }

    @GetMapping("{shopID}")
    public String getShopByID(Model model, @PathVariable long shopID){
        model.addAttribute("shop", shopUserService.findShopByShopID(shopID));
        return "shopID";
    }

}
