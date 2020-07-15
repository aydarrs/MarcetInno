package ru.innopolis.demo.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.demo.domain.ShopOrder;
import ru.innopolis.demo.repos.OrderRepository;

import java.util.Optional;

@Service
@Log4j2
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Iterable<ShopOrder> getAllOrders() {
        Iterable<ShopOrder> orders = orderRepository.findAll();
        log.info(String.format("Found and return %s orders from DB.", orderRepository.count()));
        return orders;
    }

    public ShopOrder getOrderById(long orderId) {
        Optional<ShopOrder> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            log.info("Order was found by id = " + orderId);
            return orderOptional.get();
        } else {
            log.error("Not found relative order by id = " + orderId);
            return null;
        }
    }

    public ShopOrder saveNewOrder(ShopOrder shopOrder) {
        orderRepository.save(shopOrder);
        Optional<ShopOrder> orderOptional = orderRepository.findById(shopOrder.getOrderId());
        if (orderOptional.isPresent()) {
            log.info("Order saved successful");
            return orderOptional.get();
        } else {
            log.error("Problem with saving order");
            return null;
        }
    }

    public ShopOrder changeOrderById(long orderId, ShopOrder shopOrder) {
        orderRepository.save(shopOrder);
        Optional<ShopOrder> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            log.info("Order changed successful");
            return orderOptional.get();
        } else {
            log.error("Problem with changing order");
            return null;
        }
    }
}
