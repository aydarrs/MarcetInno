package ru.innopolis.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.demo.domain.OrderShop;
import ru.innopolis.demo.service.OrderService;

@Controller
@RequestMapping("/order")

public class OrderController {

    private OrderService orderService;

    @Value("${api_key}")
    private String apiKey;

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
        String deliveryAddress = order.getDeliveryAddress();
        model.addAttribute("src",
                "https://www.google.com/maps/embed/v1/place?key=" + apiKey +
                        "&q=" + deliveryAddress);
        model.addAttribute("deliveryAddress", deliveryAddress);
        return "map";
    }


    @GetMapping("/{orderId}/yandex-map")
    public String showYandexMapByUserAddress(@PathVariable long orderId, Model model) {
        String mapPoint = "https://yandex.ru/map-widget/v1/?um=constructor%3Aa811a92e1d8b9bb53f1e46615241a42aaa764888139feb491d3a47c01dfd550c&amp;source=constructor";
        OrderShop order = orderService.getOrderById(orderId);
        String deliveryAddress = order.getUserAccount().getDeliveryAddress();
        model.addAttribute("src", mapPoint);
        model.addAttribute("deliveryAddress", deliveryAddress);
        return "map";
    }
}
