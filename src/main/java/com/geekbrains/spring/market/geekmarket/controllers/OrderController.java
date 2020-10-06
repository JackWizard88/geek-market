package com.geekbrains.spring.market.geekmarket.controllers;

import com.geekbrains.spring.market.geekmarket.entities.Order;
import com.geekbrains.spring.market.geekmarket.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.market.geekmarket.services.OrderService;
import com.geekbrains.spring.market.geekmarket.utils.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;
    private Cart cart;

    @GetMapping
    public String firstRequest(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "orders";
    }

    @GetMapping("/order/{id}")
    public String showPlacedOrder(Model model, @PathVariable Long id) {
        Order order = orderService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order doesn't exist"));;
        model.addAttribute("order", order);
        return "/order_details";
    }

    @PostMapping("/order")
    public String placeOrder(@RequestParam String name,
                             @RequestParam String address,
                             @RequestParam(name = "phone_number") String phoneNumber
    ) {
        Order order = new Order();
        order.setName(name);
        order.setAddress(address);
        order.setPhoneNumber(phoneNumber);
        order.setItems(cart.getItems());
        order.setPrice(cart.getPrice());
        orderService.createorSaveOrder(order);
        cart.newCart();     //тут по идее тоже должен быть человеческий метод для решение такой задачи
        return "redirect:/orders";
    }
}
