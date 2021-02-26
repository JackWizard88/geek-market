package com.geekbrains.spring.market.geekmarket.services;

import com.geekbrains.spring.market.geekmarket.entities.Product;
import com.geekbrains.spring.market.geekmarket.repositories.ProductRepository;
import com.geekbrains.spring.market.geekmarket.utils.PageImpl;
import com.geekbrains.spring.market.geekmarket.ws.products.ProductSOAP;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public PageImpl<Product> findAll(Specification<Product> spec, int page, int size) {
        Page<Product> products = productRepository.findAll(spec, PageRequest.of(page, size));
        return new PageImpl<>(products);
    }

    public List<ProductSOAP> findAllSOAP() {
        return productRepository.findAll().stream().map(ProductSOAP::new).collect(Collectors.toList());
    }

    public void saveEditedProductInDB(Product product) {
        productRepository.save(product);
    }


}
