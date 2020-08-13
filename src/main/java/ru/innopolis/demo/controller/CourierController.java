package ru.innopolis.demo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.demo.domain.Courier;
import ru.innopolis.demo.domain.OrderShop;
import ru.innopolis.demo.domain.OrderStatus;
import ru.innopolis.demo.service.CourierService;
import ru.innopolis.demo.service.OrderService;
import ru.innopolis.demo.service.UserService;

@Controller
@RequestMapping("/courier")
@Log4j2
public class CourierController {

    private CourierService courierService;
    private OrderService orderService;
    private UserService userService;

    @Autowired
    public CourierController(CourierService courierService, OrderService orderService,
                             UserService userService) {
        this.courierService = courierService;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public String showAllCouriers(Model model) {
        model.addAttribute("couriers", courierService.getAllCouriers());
        return "allCouriers";
    }

    @GetMapping("/free")
    public String getFreeCourierForAppointment(@RequestParam Long order_id, Model model){
        Iterable<Courier> couriers = courierService.getFreeCouriers();
        model.addAttribute("order_id", order_id);
        model.addAttribute("free_couriers", couriers);
        return "freeCouriers";
    }

    @GetMapping("/appoint/courier_id/{courier_id}/order_id/{order_id}")
    public String appointToOrder(
            @PathVariable("courier_id") int courier_id,
            @PathVariable("order_id") int order_id,
            Model model
    ){
        Courier courier = courierService.getCourierById(courier_id);
        log.info("Selected courier: " + courier);
        OrderShop order = orderService.getOrderById(order_id);
        order.setCourier(courier);
        order.setOrderStatus(OrderStatus.COURIER_APPOINTED);
        orderService.saveChanged(order);
        log.info("Changed order status: " + order);
        model.addAttribute("orders", orderService.getAllOrders());
        // Это для владельца магазина, чтобы его возвращало на свою страницу
        if (SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal().toString().contains("SELLER"))
            return "redirect:/seller/orders";
        return "redirect:/order/all";
    }

    @GetMapping("/deliver/")
    public String completeOrder(
            @RequestParam("user_name") String user_name,
            @RequestParam("order_id") int order_id,
            Model model
    ){
        Courier courier = courierService.getCourierByUser(userService.getUserByUserName(user_name));
        log.info("Selected courier: " + courier);
        OrderShop order = orderService.getOrderById(order_id);
        order.setOrderStatus(OrderStatus.COMPLETED);
        orderService.saveChanged(order);
        log.info("Changed order status: " + order);
        model.addAttribute("orders", orderService.getAllOrders());
        return "redirect:/order/courier/?userName=" + courier.getUserID().getUserName();
    }

}
