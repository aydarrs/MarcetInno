package ru.innopolis.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.demo.domain.Shop;
import ru.innopolis.demo.service.Impl.ShopServiceManager;

/**
 * ShopController
 *
 * @author Dmitrii_Blazhko 19-Jul-20
 */

@Controller
@RequestMapping("/shop/")
public class ShopControllerManager {

    private ShopServiceManager shopServiceManager;

    @Autowired
    public ShopControllerManager(ShopServiceManager shopServiceManager) {
        this.shopServiceManager = shopServiceManager;
    }

    @GetMapping("all")
    public String showAllShop(Model model) {
        model.addAttribute("all", shopServiceManager.getAllShop());
        return "all";
    }

    @GetMapping("update")
    public String updateShopByShopID (@PathVariable long shopsID) {

        return "update";
    }

    @GetMapping("add")
    public String addShop() {
        Shop shop = new Shop();
        shopServiceManager.updateShop(shop);
        return "add";
    }
}
