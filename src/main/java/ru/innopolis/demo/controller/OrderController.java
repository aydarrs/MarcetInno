package ru.innopolis.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.demo.domain.OrderShop;
import ru.innopolis.demo.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public String showAllOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "allOrders";
    }

    @GetMapping("/{orderId}")
    public OrderShop getOrderById(@PathVariable long orderId) {
        return orderService.getOrderById(orderId);
    }

    @PostMapping("/")
    public OrderShop addOrder(@RequestParam OrderShop orderShop) {
        return orderService.saveNewOrder(orderShop);
    }

    @GetMapping("/{orderId}/map")
    public String showMapByUserAddress(@PathVariable long orderId, Model model) {
        OrderShop order = orderService.getOrderById(orderId);
        String deliveryAddress = order.getUserAccount().getDeliveryAddress();
        model.addAttribute("src",
                "https://www.google.com/maps/embed/v1/place?key=api_key" +
                        "&q=" + deliveryAddress);
        model.addAttribute("deliveryAddress", deliveryAddress);
        return "map";
    }
}
