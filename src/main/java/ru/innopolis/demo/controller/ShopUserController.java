package ru.innopolis.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.demo.service.ShopService;
import ru.innopolis.demo.service.ShopUserService;


/**
 * ShopUserController
 *
 * @author Dmitrii_Blazhko
 */

@Controller
@RequestMapping("/shop")
public class ShopUserController {

    private ShopService shopService;

    @Autowired
    public ShopUserController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/all")
    public String getAllShop(Model model){
        model.addAttribute("allShop",shopService.getAllShops());
        return "allShop";
    }

    @GetMapping("/{shopID}")
    public String getShopByID(Model model, @PathVariable long shopID){
        model.addAttribute("shop", shopService.getShopByShopId(shopID));
        return "shop";
    }

}
