package ru.innopolis.demo.service;

import org.springframework.data.domain.Page;
import ru.innopolis.demo.domain.Product;

/**
 * IProductService.
 * Interface of products service.
 * @author Aydar_Safiullin
 */
public interface IProductService {
    /**
     * Find and paginate items.
     * @param pageNo - page number;
     * @param pageSize - page size.
     * @return - all items.
     */
    Page<Product> getAllProducts(int pageNo, int pageSize);

    /**
     * Find all items containing the template.
     * @param template - search request;
     * @param pageNo - page number;
     * @param pageSize - page size.
     * @return - founded items.
     */
    Page<Product> getAllProductsByTemplate(String template, int pageNo, int pageSize);

    Product getProductById(long productId);

    void saveNewProduct(Product product);

    void deleteProductById(long productId);

    void changeProductById(long productId, Product product);

    Iterable<Product> findProductsByShopID(Long shopID);
}
