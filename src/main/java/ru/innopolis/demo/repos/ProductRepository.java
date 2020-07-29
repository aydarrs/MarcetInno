package ru.innopolis.demo.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.demo.domain.Product;


@Repository
@EnableTransactionManagement
@Transactional
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    Iterable<Product> findProductsByShopShopID(long shopID);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) " +
            "LIKE CONCAT('%', LOWER(:template), '%') " +
            "OR p.article LIKE CONCAT('%', LOWER(:template), '%')")
    Page<Product> findProductsByNameContainingOrArticleContaining(@Param("template") String template, Pageable pageable);

}
