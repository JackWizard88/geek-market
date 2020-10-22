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
    private String filterDefinition;

    public ProductFilter(Map<String, String> params) {
        StringBuilder filterDefinitionBuilder = new StringBuilder();
        spec = Specification.where(null);

        String filterTitle = params.get("title");
        if (filterTitle != null && !filterTitle.trim().isEmpty()) {
            spec = spec.and(ProductSpecifications.titleLike(filterTitle));
            filterDefinitionBuilder.append("&title=").append(filterTitle);
        }

        if (params.containsKey("min_price") && !params.get("min_price").trim().isEmpty()) {
            Integer minPrice = Integer.parseInt(params.get("min_price"));
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
            filterDefinitionBuilder.append("&min_price=").append(minPrice);
        }

        if (params.containsKey("max_price") && !params.get("max_price").trim().isEmpty()) {
            Integer maxPrice = Integer.parseInt(params.get("max_price"));
            spec = spec.and(ProductSpecifications.priceLesserOrEqualsThan(maxPrice));
            filterDefinitionBuilder.append("&max_price=").append(maxPrice);
        }
        if (params.containsKey("category") && !params.get("category").trim().isEmpty()) {
            ObjectMapper om = new ObjectMapper();

            List<Category> categories = null;
            try {
                categories = om.readValue(params.get("category"), om.getTypeFactory().constructCollectionType(List.class, Category.class));
                spec = spec.and(ProductSpecifications.categoryIn(categories));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        filterDefinition = filterDefinitionBuilder.toString();
    }
}
