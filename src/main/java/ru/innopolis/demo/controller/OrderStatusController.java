package ru.innopolis.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.demo.domain.OrderShop;
import ru.innopolis.demo.domain.OrderStatus;
import ru.innopolis.demo.service.OrderService;

@Controller
@RequestMapping("/order-status")
public class OrderStatusController {

    private OrderService orderService;

    @Autowired
    public OrderStatusController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/created")
    public String ordersForCourierAppointment(Model model) {
        Iterable<OrderShop> orders = orderService.getOrdersWithStatus(OrderStatus.CREATED);
        model.addAttribute("orders", orders);
        return "ordersCreated";
    }

}
