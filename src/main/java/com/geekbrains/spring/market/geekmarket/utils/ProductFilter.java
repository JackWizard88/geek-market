package com.geekbrains.spring.market.geekmarket.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekbrains.spring.market.geekmarket.entities.Category;
import com.geekbrains.spring.market.geekmarket.entities.Product;
import com.geekbrains.spring.market.geekmarket.repositories.specifications.ProductSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;

@Getter
public class ProductFilter {
    private Specification<Product> spec;

    public ProductFilter(Map<String, String> params) {
        spec = Specification.where(null);

        String filterTitle = params.get("title");
        if (filterTitle != null && !filterTitle.trim().isEmpty()) {
            spec = spec.and(ProductSpecifications.titleLike(filterTitle));
        }

        if (params.containsKey("min_price") && !params.get("min_price").trim().isEmpty()) {
            Integer minPrice = Integer.parseInt(params.get("min_price"));
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }

        if (params.containsKey("max_price") && !params.get("max_price").trim().isEmpty()) {
            Integer maxPrice = Integer.parseInt(params.get("max_price"));
            spec = spec.and(ProductSpecifications.priceLesserOrEqualsThan(maxPrice));
        }
        if (params.containsKey("category") && !params.get("category").trim().isEmpty()) {
            Long categoryId = Long.parseLong(params.get("category"));
            spec = spec.and(ProductSpecifications.categoryIdIs(categoryId));
        }

    }
}
