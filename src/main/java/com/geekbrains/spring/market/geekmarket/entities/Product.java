package com.geekbrains.spring.market.geekmarket.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private double price;

    @OneToMany(mappedBy = "product", orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    private List<Order> orders;
}
