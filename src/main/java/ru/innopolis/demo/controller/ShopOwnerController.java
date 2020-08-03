package ru.innopolis.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.demo.service.*;

/**
 * ShopOwnerController.
 *
 * @author Aydar_Safiullin
 */
@Controller
@RequestMapping("/owner")
public class ShopOwnerController {
    private IProductService productService;
    private ShopService shopService;

    @Autowired
    public ShopOwnerController(IProductService productService,
                               ShopService shopService) {
        this.productService = productService;
        this.shopService = shopService;
    }

    @GetMapping("/{shopId}")
    public String getShopOwnerPage(Model model, @PathVariable Long shopId) {
        model.addAttribute("products", productService.findProductsByShopID(shopId));
        model.addAttribute("shop", shopService.findShopByShopID(shopId));
        return "shopOwner";
    }
}
