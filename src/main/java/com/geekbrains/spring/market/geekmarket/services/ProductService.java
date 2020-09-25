package com.geekbrains.spring.market.geekmarket.services;

import com.geekbrains.spring.market.geekmarket.entities.Product;
import com.geekbrains.spring.market.geekmarket.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> findAll(Specification<Product> spec, int page, int size) {
        return productRepository.findAll(spec, PageRequest.of(page, size));
    }

    public void saveEditedProductInDB(Long id, String title, Double price) {
        Product product = productRepository.findById(id).get();
        product.setPrice(price);
        product.setTitle(title);
        productRepository.save(product);
    }
}
