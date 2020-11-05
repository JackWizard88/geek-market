package com.geekbrains.spring.market.geekmarket.dto;

import com.geekbrains.spring.market.geekmarket.entities.Order;
import com.geekbrains.spring.market.geekmarket.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private User user;
    private Date createDate;
    private String name;
    private String address;
    private String phoneNumber;
    private double price;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.user = order.getUser();
        this.createDate = order.getCreateDate();
        this.name = order.getName();
        this.address = order.getAddress();
        this.phoneNumber = order.getPhoneNumber();
        this.price = order.getPrice();
    }
}
