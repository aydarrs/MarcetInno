package ru.innopolis.demo.controller;

import liquibase.pro.packaged.B;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import ru.innopolis.demo.domain.OrderShop;
import ru.innopolis.demo.domain.Product;
import ru.innopolis.demo.domain.UserAccount;
import ru.innopolis.demo.service.*;

import java.io.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
    private UserService userService;
    private OrderService orderService;

    @Autowired
    public SellerController(IProductService productService,
                            ShopService shopService,
                            UserService userService,
                            OrderService orderService) {
        this.productService = productService;
        this.shopService = shopService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/")
    public RedirectView selectShop(Model model) {
        String userName = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        UserAccount user = userService.getUserByUserName(userName);
        long userId = user.getUserId();
        long shopId = shopService.getShopBySellerId(userId).getShopID();
        return new RedirectView("/seller/" + shopId);
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
                                @RequestParam long shopID,
                                @RequestParam String article,
                                @RequestParam String name,
                                @RequestParam String description,
                                @RequestParam double price,
                                @RequestParam double count,
                                @RequestParam MultipartFile file) {
        String image = productService.copyImage(file, shopID, article);
        Product product = new Product();
        configureProduct(shopID,
                article,
                name,
                description,
                price,
                count,
                image,
                product);
        return getShopOwnerPage(model, shopID);
    }

    @GetMapping("/update/{productID}")
    public String redirectToUpdateProductPage(Model model,
                                              @PathVariable Long productID) {
        Product product = productService.getProductById(productID);
        model.addAttribute("product", product);
        model.addAttribute("shopID", product.getShop().getShopID());
        return "update_product";
    }

    @PostMapping("/update")
    public String updateProduct(Model model,
                                @RequestParam long productID,
                                @RequestParam long shopID,
                                @RequestParam String article,
                                @RequestParam String name,
                                @RequestParam String description,
                                @RequestParam double price,
                                @RequestParam double count,
                                @RequestParam MultipartFile file) {

        String image = productService.copyImage(file, shopID, article);
        Product product = new Product();
        product.setProductID(productID);
        configureProduct(shopID, article, name, description, price, count, image, product);
        return getShopOwnerPage(model, shopID);
    }

    @GetMapping("/orders")
    public String getShopOrdersPage(Model model) {
        String userName = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        UserAccount user = userService.getUserByUserName(userName);
        long userId = user.getUserId();
        long shopId = shopService.getShopBySellerId(userId).getShopID();
        List<Product> allProducts = (ArrayList<Product>) productService.findProductsByShopID(shopId);
        List<OrderShop> orders = new LinkedList<>();
        for (Product product : allProducts) {
            List<OrderShop> foundedOrders = (ArrayList<OrderShop>) orderService.getOrdersByProductId(product.getProductID());
            if (foundedOrders != null) {
                orders.addAll(foundedOrders);
            }
        }
        model.addAttribute("orders", orders);
        model.addAttribute("shop", shopService.getShopById(shopId));
        return "shopOrders";
    }

    private void configureProduct(@RequestParam long shopID,
                                  @RequestParam String article,
                                  @RequestParam String name,
                                  @RequestParam String description,
                                  @RequestParam double price,
                                  @RequestParam double count,
                                  @RequestParam String image,
                                  Product product) {
        product.setShop(shopService.getShopById(shopID));
        product.setArticle(article);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setProductCount((int) count);
        product.setImage(image);
        productService.saveNewProduct(product);
    }


}
