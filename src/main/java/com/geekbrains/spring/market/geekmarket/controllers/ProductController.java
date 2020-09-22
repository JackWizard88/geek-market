package com.geekbrains.spring.market.geekmarket.controllers;

import com.geekbrains.spring.market.geekmarket.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final int PAGE_SIZE = 5;
    private ProductService productService;

    @GetMapping
    public String showAllProducts(Model model, @RequestParam(required = false, defaultValue = "1", name = "p") Integer page, @RequestParam(required = false) Double min, @RequestParam(required = false) Double max) {
        if (page < 1) {
            page = 1;
        }

        if (min != null & max == null) {
            model.addAttribute("products", productService.getMaxPrice(page - 1, PAGE_SIZE, min));
        } else if (min == null & max != null) {
            model.addAttribute("products", productService.getMinPrice(page - 1, PAGE_SIZE, max));
        } else if (min != null & max != null) {
            model.addAttribute("products", productService.getMinAndMaxPrice(page - 1, PAGE_SIZE, min, max));
        } else {
            model.addAttribute("products", productService.findAll(page - 1, PAGE_SIZE));
        }
        return "products";
    }

    @GetMapping("/filter")
    public String filterProductByPrice(@RequestParam Double min, @RequestParam Double max) {

        if (min != null & max == null) {
            return "redirect:/products?min="+min;
        } else if (min == null & max != null) {
            return "redirect:/products?max="+max;
        } else if (min != null & max != null) {
            if (min > max) min = max;
            return "redirect:/products?min="+min+"&max="+max;
        } else {
            return "redirect:/products";
        }
    }

}
