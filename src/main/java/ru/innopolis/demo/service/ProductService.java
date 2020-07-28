package ru.innopolis.demo.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.innopolis.demo.domain.Product;
import ru.innopolis.demo.repos.ProductRepository;

import java.util.Optional;

@Service
@Log4j2
public class ProductService implements IProductService{

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> findProductsByShopID(Long shopID) {
        return productRepository.findProductsByShopShopID(shopID);
    }

    @Override
    public Page<Product> getAllProducts(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo - 1, pageSize);
        Page<Product> page = productRepository.findAll(paging);
        return page;
    }

    @Override
    public Page<Product> getAllProductsByTemplate(String template, int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo - 1, pageSize);
        Page<Product> page = productRepository.findProductsByNameContainingIgnoreCase(template, paging);
        return page;
    }

    @Override
    public Product getProductById(long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        return productOptional.orElse(null);
    }

    @Override
    public void saveNewProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProductById(long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public void changeProductById(long productId, Product product) {
        saveNewProduct(product);
        if (null != getProductById(productId)) {
            log.info("Product {} changed", productId);
        } else log.error("Error by changing product");
    }
}
