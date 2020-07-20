package ru.innopolis.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.innopolis.demo.service.ProductService;

@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/shop/{shopID}")
    public String getAllProducts(Model model, @PathVariable Long shopID) {
        model.addAttribute("products", productService.findProductsByShopID(shopID));
        return "shopProducts";
    }
}
