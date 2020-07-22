package ru.innopolis.demo.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.demo.domain.Product;
import ru.innopolis.demo.repos.ProductRepository;

import java.util.Optional;

@Service
@Log4j2
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> findProductsByShopID(Long shopID) {
        return productRepository.findProductsByShopShopID(shopID);
    }

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        return productOptional.orElse(null);
    }

    public void saveNewProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProductById(long productId) {
        productRepository.deleteById(productId);
    }

    public void changeProductById(long productId, Product product) {
        saveNewProduct(product);
        if (null != getProductById(productId)) {
            log.info("Product {} changed", productId);
        } else log.error("Error by changing product");
    }
}
