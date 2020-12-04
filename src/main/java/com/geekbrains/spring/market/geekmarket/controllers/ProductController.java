package com.geekbrains.spring.market.geekmarket.controllers;

import com.geekbrains.spring.market.geekmarket.entities.Product;
import com.geekbrains.spring.market.geekmarket.services.ProductService;
import com.geekbrains.spring.market.geekmarket.utils.PageImpl;
import com.geekbrains.spring.market.geekmarket.utils.ProductFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(produces = "application/json")
    public PageImpl<Product> getAllProducts(@RequestParam(defaultValue = "1", name = "p") Integer page,
                                            @RequestParam Map<String, String> params
    ) {
        if (page < 1) {
            page = 1;
        }
        System.err.println(params);
        ProductFilter productFilter = new ProductFilter(params);
        PageImpl<Product> content = productService.findAll(productFilter.getSpec(), page - 1, 5);
        return content;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id).get();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product p) {
        p.setId(null);
        return productService.saveOrUpdate(p);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product p) {
        return productService.saveOrUpdate(p);
    }

    @DeleteMapping
    public void deleteAll() {
        productService.deleteAll();
    }

    @DeleteMapping("admin/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
