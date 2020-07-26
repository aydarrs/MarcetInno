package ru.innopolis.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.demo.domain.Product;
import ru.innopolis.demo.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/shop/{shopID}")
    public String getAllProducts(Model model, @PathVariable Long shopID) {
        model.addAttribute("shopProducts", productService.findProductsByShopID(shopID));
        return "shopProducts";
    }

    @GetMapping("/all")
    public String showAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "allProducts";
    }

    @GetMapping("/{productId}")
    public String showOneProducts(Model model, @PathVariable Long productId) {
        model.addAttribute("product", productService.getProductById(productId));
        return "oneProduct";
    }

    @GetMapping("/add/{product}")
    public String saveProduct(Model model, @PathVariable Product product) {
        productService.saveNewProduct(product);
        return showAllProducts(model);
    }

    @GetMapping("/update/{productId}/{product}")
    public String changeProduct(Model model, @PathVariable Long productId ,@PathVariable Product product) {
        productService.changeProductById(productId, product);
        return showAllProducts(model);
    }

    @GetMapping("/delete/{productId}")
    public String deleteProduct(Model model, @PathVariable Long productId) {
        productService.deleteProductById(productId);
        return showAllProducts(model);
    }
}
