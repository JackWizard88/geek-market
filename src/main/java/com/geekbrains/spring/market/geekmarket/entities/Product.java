package com.geekbrains.spring.market.geekmarket.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="category_id")
    private Category category;

    @Column(name = "price")
    private double price;
}
