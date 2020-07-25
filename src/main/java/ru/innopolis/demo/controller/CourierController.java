package ru.innopolis.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.demo.service.CourierService;

@Controller
@RequestMapping("/courier")
public class CourierController {

    private CourierService courierService;

    @Autowired
    public CourierController(CourierService courierService) {
        this.courierService = courierService;
    }

    @GetMapping("/all")
    public String showAllCouriers(Model model) {
        model.addAttribute("couriers", courierService.getAllCouriers());
        return "allCouriers";
    }
}
