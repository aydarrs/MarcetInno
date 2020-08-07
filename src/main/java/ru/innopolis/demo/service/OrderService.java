package ru.innopolis.demo.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.demo.domain.Courier;
import ru.innopolis.demo.domain.OrderShop;
import ru.innopolis.demo.domain.OrderStatus;
import ru.innopolis.demo.domain.UserAccount;
import ru.innopolis.demo.repos.OrderRepository;

import java.util.Optional;

@Service
@Log4j2
public class OrderService {
    //TODO: Нужны комментарии
    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Iterable<OrderShop> getAllOrders() {
        Iterable<OrderShop> orders = orderRepository.findAllOrdered();
        log.info(String.format("Found and return %s orders from DB.", orderRepository.count()));
        return orders;
    }

    public OrderShop getOrderById(long orderId) {
        Optional<OrderShop> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            log.info("Order was found by id = " + orderId);
            return orderOptional.get();
        } else {
            log.error("Not found relative order by id = " + orderId);
            return null;
        }
    }

    public OrderShop saveNewOrder(OrderShop orderShop) {
        orderRepository.save(orderShop);
        Optional<OrderShop> orderOptional = orderRepository.findById(orderShop.getOrderId());
        if (orderOptional.isPresent()) {
            log.info("Order saved successful");
            return orderOptional.get();
        } else {
            log.error("Problem with saving order");
            return null;
        }
    }

    public OrderShop changeOrderById(long orderId, OrderShop orderShop) {
        orderRepository.save(orderShop);
        Optional<OrderShop> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            log.info("Order changed successful");
            return orderOptional.get();
        } else {
            log.error("Problem with changing order");
            return null;
        }
    }

    public Iterable<OrderShop> getOrdersWithStatus(OrderStatus orderStatus) {
        log.info("Found orders by status.");
        return orderRepository.findAllByOrderStatusOrderByOrderId(orderStatus);
    }

    public Iterable<OrderShop> getOrdersForCourier(Courier courier) {
        log.info("Found orders by courier.");
        return orderRepository.findAllByCourierOrderByOrderId(courier);
    }

    public void saveChanged(OrderShop order) {
        orderRepository.save(order);
        log.info("Saved changes for order: " + order);
    }

    public Iterable<OrderShop> getOrdersByUserAccount(UserAccount userAccount) {
        return orderRepository.findOrderShopsByUserAccountOrderByOrderId(userAccount);
    }
}
