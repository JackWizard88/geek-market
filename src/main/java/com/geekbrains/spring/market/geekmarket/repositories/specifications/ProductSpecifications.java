package com.geekbrains.spring.market.geekmarket.repositories.specifications;

import com.geekbrains.spring.market.geekmarket.entities.Category;
import com.geekbrains.spring.market.geekmarket.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;
import java.util.List;

public class ProductSpecifications {
    public static Specification<Product> priceGreaterOrEqualsThan(int minPrice) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);  // where p.price >= minPrice
    }

    public static Specification<Product> priceLesserOrEqualsThan(int maxPrice) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice); // where p.price <= maxPrice
    }

    public static Specification<Product> titleLike(String titlePart) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart)); // where p.title like %titlePart%
    }

    public static Specification<Product> categoryIn(List<Category> categories) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            Path<Category> rootCategory = root.get("category");
            return rootCategory.in(categories);
        };
    }
}
