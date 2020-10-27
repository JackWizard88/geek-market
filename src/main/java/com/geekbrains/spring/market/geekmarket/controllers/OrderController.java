package com.geekbrains.spring.market.geekmarket.controllers;

import com.geekbrains.spring.market.geekmarket.entities.Order;
import com.geekbrains.spring.market.geekmarket.entities.User;
import com.geekbrains.spring.market.geekmarket.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.market.geekmarket.services.OrderService;
import com.geekbrains.spring.market.geekmarket.services.UserService;
import com.geekbrains.spring.market.geekmarket.utils.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private UserService userService;
    private OrderService orderService;
    private Cart cart;

    @GetMapping
    public String getOrderListForUser(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("orders", orderService.findByUser(user));
        model.addAttribute("principal", principal);
        return "orders";
    }

    @GetMapping("/order/{id}")
    public String showPlacedOrder(Principal principal, Model model, @PathVariable Long id) {
        Order order = orderService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order doesn't exist"));;
        model.addAttribute("order", order);
        model.addAttribute("principal", principal);
        return "/order_details";
    }

    @PostMapping("/order")
    public String placeOrder(Principal principal, Model model,
                             @RequestParam String name,
                             @RequestParam String address,
                             @RequestParam(name = "phone_number") String phoneNumber
    ) {
        model.addAttribute("principal", principal);
        User user = userService.findByUsername(principal.getName());
        Order order = new Order();
        order.setUser(user);
        order.setName(name);
        order.setAddress(address);
        order.setPhoneNumber(phoneNumber);
        order.setItems(cart.getItems());
        order.setPrice(cart.getPrice());
        orderService.createorSaveOrder(order);
        cart.newCart();
        return "redirect:/orders";
    }
}
