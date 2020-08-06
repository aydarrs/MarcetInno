package ru.innopolis.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.innopolis.demo.domain.Product;
import ru.innopolis.demo.service.*;

/**
 * ShopOwnerController.
 *
 * @author Aydar_Safiullin
 */
@Controller
@RequestMapping("/seller")
public class SellerController {
    private IProductService productService;
    private ShopService shopService;

    @Autowired
    public SellerController(IProductService productService,
                            ShopService shopService) {
        this.productService = productService;
        this.shopService = shopService;
    }

    @GetMapping("/{shopId}")
    public String getShopOwnerPage(Model model, @PathVariable Long shopId) {
        model.addAttribute("products", productService.findProductsByShopID(shopId));
        model.addAttribute("shop", shopService.getShopById(shopId));
        return "shopOwner";
    }

    @GetMapping("/delete")
    public RedirectView deleteSelectedProduct(Model model,
                                        @RequestParam Long productID,
                                        @RequestParam Long shopID) {
        productService.deleteProductById(productID);
        return new RedirectView("/seller/" + shopID);
    }

    @GetMapping("/add/")
    public String redirectToAddProductPage(Model model,
                                           @RequestParam Long shopID) {
        model.addAttribute("shopID", shopID);
        return "add_product";
    }

    @PostMapping("/add")
    public String addNewProduct(Model model,
                                      @RequestParam Long shopID,
                                      @RequestParam String article,
                                      @RequestParam String name,
                                      @RequestParam String description,
                                      @RequestParam Double price,
                                      @RequestParam Integer count) {
        Product product = new Product();
        configureProduct(shopID,
                         article,
                         name,
                         description,
                         price,
                         count,
                         product);
        return getShopOwnerPage(model, shopID);
    }

    @GetMapping("/rupdate/{productID}")
    public String redirectToUpdateProductPage(Model model,
                                              @PathVariable Long productID) {
        Product product = productService.getProductById(productID);
        model.addAttribute("product", product);
        model.addAttribute("shopID", product.getShop().getShopID());
        return "update_product";
    }

    @PostMapping("/update")
    public String updateProduct(Model model,
                                @RequestParam Long productID,
                                @RequestParam Long shopID,
                                @RequestParam String article,
                                @RequestParam String name,
                                @RequestParam String description,
                                @RequestParam Double price,
                                @RequestParam Integer count) {
        Product product = new Product();
        product.setProductID(productID);
        configureProduct(shopID, article, name, description, price, count, product);
        return getShopOwnerPage(model, shopID);
    }

    private void configureProduct(@RequestParam Long shopID, @RequestParam String article, @RequestParam String name, @RequestParam String description, @RequestParam Double price, @RequestParam Integer count, Product product) {
        product.setShop(shopService.getShopById(shopID));
        product.setArticle(article);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setProductCount(count);
        productService.saveNewProduct(product);
    }


}