package com.geekbrains.spring.market.geekmarket.repositories;

import com.geekbrains.spring.market.geekmarket.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
