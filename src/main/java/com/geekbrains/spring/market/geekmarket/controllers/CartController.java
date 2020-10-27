package com.geekbrains.spring.market.geekmarket.controllers;

import com.geekbrains.spring.market.geekmarket.entities.Product;
import com.geekbrains.spring.market.geekmarket.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.market.geekmarket.services.ProductService;
import com.geekbrains.spring.market.geekmarket.utils.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private ProductService productService;
    private Cart cart;

    @GetMapping
    public String showCartPage(Principal principal, Model model, HttpSession session) {
        model.addAttribute("principal", principal);
        return "cart";
    }

    @GetMapping("/add/{product_id}")
    public void addToCart(
            @PathVariable(name = "product_id") Long productId,
            HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
        Product p = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + productId + " doesn't exists (add to cart"));
        cart.addOrIncrement(p);
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping("/inc/{product_id}")
    public String addOrIncrementProduct(@PathVariable(name = "product_id") Long productId) {
        cart.incrementOnly(productId);
        return "redirect:/cart";
    }

    @GetMapping("/dec/{product_id}")
    public String decrementOrRemoveProduct(@PathVariable(name = "product_id") Long productId) {
        cart.decrementOrRemove(productId);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{product_id}")
    public String removeProduct(@PathVariable(name = "product_id") Long productId) {
        cart.remove(productId);
        return "redirect:/cart";
    }

    @GetMapping("/order")
    public String showOrder() {
        return "order";
    }

}
