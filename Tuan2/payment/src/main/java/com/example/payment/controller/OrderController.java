package com.example.payment.controller;

import com.example.payment.factory.abstractfactory.MomoFactory;
import com.example.payment.factory.abstractfactory.VnPayFactory;
import com.example.payment.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam String type,
                           @RequestParam double amount) {
        orderService.checkout(type, amount);
        return "Checkout success";
    }

    @PostMapping("/checkout/vnpay")
    public String checkoutVnPay(@RequestParam double amount) {
        orderService.checkoutWithRefund(new VnPayFactory(), amount);
        return "VNPay checkout success";
    }

    @PostMapping("/checkout/momo")
    public String checkoutMomo(@RequestParam double amount) {
        orderService.checkoutWithRefund(new MomoFactory(), amount);
        return "MoMo checkout success";
    }
}
