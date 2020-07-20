package ru.innopolis.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.demo.domain.OrderShop;
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
    public Iterable<OrderShop> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public OrderShop getOrderById(@PathVariable long orderId) {
        return orderService.getOrderById(orderId);
    }

    @PostMapping("/")
    public OrderShop addOrder(@RequestParam OrderShop orderShop) {
        return orderService.saveNewOrder(orderShop);
    }

    @PostMapping("/{orderId}")
    public OrderShop changeOrder(@PathVariable long orderId, @RequestParam OrderShop orderShop) {
        return orderService.changeOrderById(orderId, orderShop);
    }

}
