package ru.innopolis.demo.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.demo.domain.Product;
import ru.innopolis.demo.repos.ProductRepository;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
@Log4j2
public class ProductService implements IProductService{

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> findProductsByShopID(Long shopID, int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo - 1, pageSize);
        Page<Product> page = productRepository.findProductsByShopShopIDOrderByProductID(paging, shopID);
        return page;
    }

    @Override
    public String copyImage(MultipartFile file, long shopID, String article) {
        String image = "default-image.jpg";
        if (file != null) {
            image = "shop-id-" + shopID + "/" + article + ".jpg";
            try (BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(
                    new File("target/classes/static/images/product/" + image)
            ))) {
                byte[] bytes = file.getBytes();
                writer.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return image;
    }

    @Override
    public Page<Product> getAllProducts(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo - 1, pageSize);
        Page<Product> page = productRepository.findAllOrdered(paging);
        return page;
    }

    @Override
    public Page<Product> getAllProductsByTemplate(String template, int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo - 1, pageSize);
        Page<Product> page = productRepository.findProductsByNameContainingOrArticleContaining(template, paging);
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

    @Override
    public Iterable<Product> findProductsByShopID(Long shopId) {
        return productRepository.findProductsByShopShopIDOrderByProductID(shopId);
    }
}
