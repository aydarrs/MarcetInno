package ru.innopolis.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.demo.domain.Shop;
import ru.innopolis.demo.service.ShopService;
import ru.innopolis.demo.service.ShopServiceManager;

@Controller
@RequestMapping("/shop/admin")
public class ShopManagerController {

    private ShopService shopService;
    private ShopServiceManager shopServiceManager;

    @Autowired
    public ShopManagerController(ShopService shopService, ShopServiceManager shopServiceManager) {
        this.shopService = shopService;
        this.shopServiceManager = shopServiceManager;
    }

    @GetMapping("/all")
    public String getAllShop(Model model) {
        model.addAttribute("shops", shopService.getAllShops());
        return "shops";
    }

    @GetMapping("/{shopID}")
    public String showShopByShopID(Model model, @PathVariable Long shopID) {
        model.addAttribute("shop", shopService.findShopByShopID(shopID));
        return "oneShop";
    }

    @PostMapping("/add")
    public void addShop(@PathVariable Shop shop) {
       shopServiceManager.saveNewShop(shop);
    }

    @GetMapping("/change/{shopID}")
    public void changeShopByID(@PathVariable Long shopID, @PathVariable Shop shop) {
       shopServiceManager.changeShopByShopID(shopID, shop);
    }

    @GetMapping("/delete/{shopID}")
    public void deleteShopByID(@PathVariable Long shopID) {
        shopServiceManager.deleteShopByShopID(shopID);
    }
}
