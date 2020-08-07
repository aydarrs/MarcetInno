package ru.innopolis.demo.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.demo.domain.Courier;
import ru.innopolis.demo.domain.OrderShop;
import ru.innopolis.demo.domain.OrderStatus;
import ru.innopolis.demo.domain.UserAccount;

@Repository
@EnableTransactionManagement
@Transactional
//TODO: Нужны комментарии
public interface OrderRepository extends CrudRepository<OrderShop, Long> {

    Iterable<OrderShop> findAllByOrderStatusOrderByOrderId(OrderStatus orderStatus);

    Iterable<OrderShop> findAllByCourierOrderByOrderId(Courier courier);

    @Query("select o from OrderShop o order by o.orderId")
    Iterable<OrderShop> findAllOrdered();

    Iterable<OrderShop> findOrderShopsByUserAccountOrderByOrderId(UserAccount userAccount);
}
