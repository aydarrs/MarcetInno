package ru.innopolis.demo.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.demo.domain.OrderShop;

@Repository
@EnableTransactionManagement
@Transactional
//TODO: Нужны комментарии
public interface OrderRepository extends CrudRepository<OrderShop, Long> {
}
