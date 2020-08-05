package ru.innopolis.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.demo.domain.Product;
import ru.innopolis.demo.service.IProductService;
import ru.innopolis.demo.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/product")
public class ProductController {

    private IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/shop/{shopID}")
    public String getAllProducts(Model model, @PathVariable Long shopID, @RequestParam("pageNo") int pageNo) {
        Page<Product> page = productService.findProductsByShopID(shopID, pageNo, 6);
        List<Integer> pageNumbers = IntStream.rangeClosed(1, page.getTotalPages())
                .boxed()
                .collect(Collectors.toList());
        model.addAttribute("page",page);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("pageNumbers", pageNumbers);
        return "allProducts";
    }

    @GetMapping("/all")
    public String showAllProducts(Model model, @RequestParam("pageNo") int pageNo) {
        Page<Product> page = productService.getAllProducts(pageNo, 6);
        // Это для генерации номеров страниц, для переключения между ними
        List<Integer> pageNumbers = IntStream.rangeClosed(1, page.getTotalPages())
                .boxed()
                .collect(Collectors.toList());
        model.addAttribute("page",page);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("pageNumbers", pageNumbers);
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
        return showAllProducts(model, 1);
    }

    @GetMapping("/update/{productId}/{product}")
    public String changeProduct(Model model,
                                @PathVariable Long productId,
                                @PathVariable Product product) {
        productService.changeProductById(productId, product);
        return showAllProducts(model, 1);
    }

    @GetMapping("/delete/{productId}")
    public String deleteProduct(Model model, @PathVariable Long productId) {
        productService.deleteProductById(productId);
        return showAllProducts(model, 1);
    }

    @GetMapping("/search")
    public String searchByNameOrArticle(Model model,
                                        @RequestParam("pageNo") int pageNo,
                                        @RequestParam("template") String template,
                                        @PageableDefault(size = 6) Pageable pageable) {
        Page<Product> page = productService.getAllProductsByTemplate(template, pageNo, 6);
        // Это для генерации номеров страниц, для переключения между ними
        List<Integer> pageNumbers = IntStream.rangeClosed(1, page.getTotalPages())
                                             .boxed()
                                             .collect(Collectors.toList());
        model.addAttribute("page", page);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("template", template);
        model.addAttribute("pageNumbers", pageNumbers);
        return "search";
    }

}
