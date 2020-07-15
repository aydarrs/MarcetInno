package ru.innopolis.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.demo.domain.ShopOrder;
import ru.innopolis.demo.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public Iterable<ShopOrder> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public ShopOrder getOrderById(@PathVariable long orderId) {
        return orderService.getOrderById(orderId);
    }

    @PostMapping("/")
    public ShopOrder addOrder(@RequestParam ShopOrder shopOrder) {
        return orderService.saveNewOrder(shopOrder);
    }

    @PostMapping("/{orderId}")
    public ShopOrder changeOrder(@PathVariable long orderId, @RequestParam ShopOrder shopOrder) {
        return orderService.changeOrderById(orderId, shopOrder);
    }

}
