package ru.innopolis.demo.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.demo.domain.Product;

@Repository
@EnableTransactionManagement
@Transactional
public interface ProductRepository extends CrudRepository<Product, Long> {

    Iterable<Product> findProductsByShopShopID(long shopID);
}
