package com.geekbrains.spring.market.geekmarket.services;

import com.geekbrains.spring.market.geekmarket.entities.Category;
import com.geekbrains.spring.market.geekmarket.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

}
