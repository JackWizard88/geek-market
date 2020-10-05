package com.geekbrains.spring.market.geekmarket.services;

import com.geekbrains.spring.market.geekmarket.entities.Product;
import com.geekbrains.spring.market.geekmarket.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public Page<Product> findAll(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    public Page<Product> getMaxPrice(int page, int size, Double max) {
        return productRepository.getProductByPriceGreaterThanEqual(max, PageRequest.of(page, size));
    }

    public Page<Product> getMinPrice(int page, int size, Double min) {
        return productRepository.getProductByPriceLessThanEqual(min, PageRequest.of(page, size));
    }

    public Page<Product> getMinAndMaxPrice(int page, int size, Double min, Double max) {
        return productRepository.getProductByPriceGreaterThanEqualAndPriceLessThanEqual(min, max, PageRequest.of(page, size));
    }
}
