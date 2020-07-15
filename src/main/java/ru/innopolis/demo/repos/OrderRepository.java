package ru.innopolis.demo.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.demo.domain.ShopOrder;

@Repository
@EnableTransactionManagement
@Transactional
public interface OrderRepository extends CrudRepository<ShopOrder, Long> {

}
