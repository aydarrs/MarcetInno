package ru.innopolis.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.demo.domain.*;
import ru.innopolis.demo.service.CourierService;
import ru.innopolis.demo.service.OrderService;
import ru.innopolis.demo.service.ProductService;
import ru.innopolis.demo.service.UserService;

import java.security.Principal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Controller
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;
    private CourierService courierService;
    private UserService userService;
    private ProductService productService;

    @Value("${api_key}")
    private String apiKey;

    @Autowired
    public OrderController(OrderService orderService,
                           CourierService courierService,
                           UserService userService,
                           ProductService productService) {
        this.orderService = orderService;
        this.courierService = courierService;
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/all")
    public String showAllOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "allOrders";
    }

    @GetMapping("/customer/list")
    public String showOrdersByCustomerId(Model model, @RequestParam String userName) {
        UserAccount userAccount = userService.getUserByUserName(userName);
        model.addAttribute("orders", orderService.getOrdersByUserAccount(userAccount));
        return "allOrders";
    }

    @GetMapping("/{orderId}")
    public String getOrderById(Model model, @PathVariable long orderId) {
        model.addAttribute("order", orderService.getOrderById(orderId));
        return "oneOrder";
    }

    @GetMapping("/status/created")
    public String ordersForCourierAppointment(Model model) {
        Iterable<OrderShop> orders = orderService.getOrdersWithStatus(OrderStatus.CREATED);
        model.addAttribute("orders", orders);
        return "allOrders";
    }

    @GetMapping("/status/reset")
    public String resetStatusForAllOrders(Model model) {
        Iterable<OrderShop> orders = orderService.getAllOrders();

        Iterator<OrderShop> itr = orders.iterator();
        while (itr.hasNext()) {
            OrderShop order = itr.next();
            order.setOrderStatus(OrderStatus.CREATED);
            order.setCourier(null);
            orderService.saveChanged(order);
        }

        model.addAttribute("orders", orderService.getAllOrders());
        // Это для владельца магазина, чтобы его возвращало на свою страницу
        if (SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal().toString().contains("SELLER"))
            return "redirect:/seller/orders";
        return "redirect:/order/all";
    }

    @GetMapping("/courier/{courierId}")
    public String showOrdersForCourier(Model model, @PathVariable long courierId) {

        Iterable<OrderShop> orders =
                orderService.getOrdersForCourier(courierService.getCourierById(courierId));
        model.addAttribute("orders", orders);
        return "allOrders";
    }

    @GetMapping("/courier/")
    public String showOrdersForCourier(Model model, @RequestParam("userName") String userName) {

        Iterable<OrderShop> orders =
                orderService.getOrdersForCourier(
                        courierService.getCourierByUser(userService.getUserByUserName(userName))
                );

        Iterator<OrderShop> itr = orders.iterator();
        while (itr.hasNext()) {
            OrderShop order = itr.next();
            if (order.getOrderStatus() == OrderStatus.COMPLETED)
                itr.remove();
        }

        model.addAttribute("orders", orders);
        return "allOrders";
    }

    @GetMapping("/{orderId}/map")
    public String showMapByUserAddress(@PathVariable long orderId, Model model) {
        OrderShop order = orderService.getOrderById(orderId);
        String deliveryAddress = order.getDeliveryAddress();
        model.addAttribute("src",
                "https://www.google.com/maps/embed/v1/place?key=" + apiKey +
                        "&q=" + deliveryAddress);
        model.addAttribute("order", order);
        return "map";
    }


    @GetMapping("/{orderId}/yandex-map")
    public String showYandexMapByUserAddress(@PathVariable long orderId, Model model) {
        String mapPoint = "https://yandex.ru/map-widget/v1/?um=constructor%3Aa811a92e1d8b9bb53f1e46615241a42aaa764888139feb491d3a47c01dfd550c&amp;source=constructor";
        OrderShop order = orderService.getOrderById(orderId);
        String deliveryAddress = order.getDeliveryAddress();
        model.addAttribute("src", mapPoint);
        model.addAttribute("deliveryAddress", deliveryAddress);
        return "map";
    }

    @GetMapping("/buy/{productID}")
    public String addOrder(Model model, @PathVariable long productID) {
        Product product = productService.getProductById(productID);
        OrderShop order = new OrderShop();
        order.setProduct(product);

        model.addAttribute("order", order);
        return "orderForm";
    }

    @PostMapping("/buy/{productID}")
    public String addOrder(@ModelAttribute OrderShop order, Model model, @PathVariable long productID, Principal principal) {
        Product product = productService.getProductById(productID);

        order.setUserAccount(userService.getUserByUserName(principal.getName()));
        order.setDate(OffsetDateTime.now());
        order.setPaymentStatus(PaymentStatus.PAID);
        order.setOrderStatus(OrderStatus.CREATED);
        order.setProduct(product);
        model.addAttribute("order", order);

        product.setProductCount(product.getProductCount() - order.getCountProduct());

        if (ThreadLocalRandom.current().nextInt(2) == 0) {
            orderService.saveNewOrder(order);
            productService.changeProductById(productID, product);
            return "successful";
        }
        return "failed";
    }

    @GetMapping("/stats")
    public String showOrderStats(Model model) {
        Iterable<OrderShop> orders = orderService.getAllOrders();

        Map<LocalDate, Integer> dateOrderCount = new LinkedHashMap<>();

        for (OrderShop order : orders) {

            LocalDate orderDate = order.getDate().toLocalDate();

            if (dateOrderCount.containsKey(orderDate)) {
                Integer orderCount = dateOrderCount.get(orderDate);

                dateOrderCount.replace(
                        orderDate,
                        orderCount + order.getCountProduct()
                );
            } else {
                dateOrderCount.put(orderDate, order.getCountProduct());
            }
        }

        model.addAttribute("dateOrderCount", dateOrderCount);
        return "orderStats";
    }

}
