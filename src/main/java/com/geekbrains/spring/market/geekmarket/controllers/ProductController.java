package com.geekbrains.spring.market.geekmarket.controllers;

import com.geekbrains.spring.market.geekmarket.entities.Product;
import com.geekbrains.spring.market.geekmarket.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.market.geekmarket.repositories.ProductRepository;
import com.geekbrains.spring.market.geekmarket.services.ProductService;
import com.geekbrains.spring.market.geekmarket.utils.ProductFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping
    public String showAllProducts(Model model,
                                  @RequestParam(defaultValue = "1", name = "p") Integer page,
                                  @RequestParam Map<String, String> params
    ) {
        if (page < 1) {
            page = 1;
        }
        ProductFilter productFilter = new ProductFilter(params);
        Page<Product> products = productService.findAll(productFilter.getSpec(), page - 1, 5);
        model.addAttribute("products", products);
        model.addAttribute("filterDefinition", productFilter.getFilterDefinition());
        return "products";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Product getOneProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exists"));
    }

    @GetMapping("/delete/{id}")
    public String deleteOneProductById(@PathVariable Long id,
                                       @RequestParam(defaultValue = "1", name = "p") Integer page,
                                       @RequestParam Map<String, String> params) {
        ProductFilter productFilter = new ProductFilter(params);
        productService.deleteById(id);
        return "redirect:/products?p=" + page + productFilter.getFilterDefinition();
    }

    @GetMapping("/edit/{id}")
    public String editOneProductById(Model model, @PathVariable Long id) {
        Product product = productService.findById(id).get();
        model.addAttribute("product", product);
        return "/edit";
    }

    @GetMapping("/edit")
    public String editOneProduct(@RequestParam Long id,
                                 @RequestParam String title,
                                 @RequestParam Double price
    ) {
        productService.saveEditedProductInDB(id, title, price);
        return "redirect:/products";
    }
}
