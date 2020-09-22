package com.geekbrains.spring.market.geekmarket.repositories;

import com.geekbrains.spring.market.geekmarket.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> getProductByPriceGreaterThanEqual(Double min, Pageable pageable);

    Page<Product> getProductByPriceLessThanEqual(Double max, Pageable pageable);

    Page<Product> getProductByPriceGreaterThanEqualAndPriceLessThanEqual(Double min, Double max, Pageable pageable);
}
