package com.geekbrains.spring.market.geekmarket.services;

import com.geekbrains.spring.market.geekmarket.dto.OrderDto;
import com.geekbrains.spring.market.geekmarket.entities.Order;
import com.geekbrains.spring.market.geekmarket.entities.OrderItem;
import com.geekbrains.spring.market.geekmarket.entities.User;
import com.geekbrains.spring.market.geekmarket.repositories.OrderRepository;
import com.geekbrains.spring.market.geekmarket.ws.orders.OrderSOAP;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findByUser(User user) {return orderRepository.findOrdersByUserEquals(user); }

    public List<OrderSOAP> findByUserSOAP(User user) {
        return orderRepository.findOrdersByUserEquals(user).stream().map(OrderDto::new).map(OrderSOAP::new).collect(Collectors.toList());
    }

    public void createorSaveOrder(Order order) {
        for (OrderItem o : order.getItems()) {
            o.setOrder(order);
        }
        orderRepository.save(order);
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }
}
