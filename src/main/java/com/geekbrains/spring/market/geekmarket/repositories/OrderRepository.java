package com.geekbrains.spring.market.geekmarket.repositories;

import com.geekbrains.spring.market.geekmarket.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
