package ru.innopolis.demo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.demo.domain.Courier;
import ru.innopolis.demo.domain.OrderShop;
import ru.innopolis.demo.domain.OrderStatus;
import ru.innopolis.demo.service.CourierService;
import ru.innopolis.demo.service.OrderService;

@Controller
@RequestMapping("/courier")
@Log4j2
public class CourierController {

    private CourierService courierService;
    private OrderService orderService;

    @Autowired
    public CourierController(CourierService courierService, OrderService orderService) {
        this.courierService = courierService;
        this.orderService = orderService;
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
        log.info("Before");
        Courier courier = courierService.getCourierById(courier_id);
        log.info("courier: " + courier);
        OrderShop order = orderService.getOrderById(order_id);
        log.info("order: " + order);

        courier.setOrderShop(order);
        courierService.saveChanged(courier);
        model.addAttribute("orders", orderService.getOrdersWithStatus(OrderStatus.CREATED));
        return "ordersCreated";
    }
}
