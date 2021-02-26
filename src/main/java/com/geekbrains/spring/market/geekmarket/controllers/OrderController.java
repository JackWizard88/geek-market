package com.geekbrains.spring.market.geekmarket.controllers;

import com.geekbrains.spring.market.geekmarket.dto.OrderDto;
import com.geekbrains.spring.market.geekmarket.dto.OrderItemDto;
import com.geekbrains.spring.market.geekmarket.entities.Order;
import com.geekbrains.spring.market.geekmarket.entities.User;
import com.geekbrains.spring.market.geekmarket.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.market.geekmarket.services.OrderService;
import com.geekbrains.spring.market.geekmarket.services.UserService;
import com.geekbrains.spring.market.geekmarket.utils.Cart;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final Cart cart;

    @GetMapping(produces = "application/json")
    public List<OrderDto> getOrderListForUser(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return orderService.findByUser(user).stream().map(OrderDto::new).collect(Collectors.toList());
    }

    @PostMapping("/place")
    public void placeOrder(Principal principal,
                             @RequestParam String name,
                             @RequestParam String address,
                             @RequestParam(name = "phone_number") String phoneNumber
    ) {
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
    }
}
