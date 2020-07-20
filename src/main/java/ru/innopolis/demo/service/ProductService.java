package ru.innopolis.demo.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.innopolis.demo.domain.Product;
import ru.innopolis.demo.repos.ProductRepository;

@Service
@Log4j2
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> findProductsByShopID(Long shopID) {
        return productRepository.findProductsByShopShopID(shopID);
    }
}
