package ru.innopolis.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.demo.domain.Shop;
import ru.innopolis.demo.service.ShopServiceManager;

/**
 * ShopController
 *
 * @author Dmitrii_Blazhko 19-Jul-20
 */

@Controller
@RequestMapping("/shop")
public class ShopManagerController {

    private ShopServiceManager shopServiceManager;

    @Autowired
    public ShopManagerController(ShopServiceManager shopService) {
        this.shopServiceManager = shopService;
    }

    @GetMapping("/all")
    public String getAllShop(Model model) {
        model.addAttribute("allShop", shopServiceManager.getAllShops());
        return "allShop";
    }

    @GetMapping("/{shopID}")
    public String showShopByShopID(Model model, @PathVariable Long shopID) {
        model.addAttribute("shop", shopServiceManager.getShopByShopId(shopID));
        return "shop";
    }

    @PostMapping("/add/{shopID}")
    public void addNewShop(Model model, @PathVariable Shop shop) {
        shopServiceManager.addNewShop(shop);
        getAllShop(model);
    }

    @PostMapping("/change/{shopID}")
    public void changeShopByShopID(Model model, @PathVariable Long shopID, @PathVariable Shop shop) {
        shopServiceManager.changeShopByShopID(shopID, shop);
        getAllShop(model);
    }

    @PostMapping("/delete/{shopID}")
    public void deleteShopByShopID(Model model, @PathVariable Long shopID) {
        shopServiceManager.deleteShopByShopID(shopID);
        getAllShop(model);
    }
}
