package com.geekbrains.spring.market.geekmarket.controllers;

import com.geekbrains.spring.market.geekmarket.entities.Order;
import com.geekbrains.spring.market.geekmarket.entities.Product;
import com.geekbrains.spring.market.geekmarket.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.market.geekmarket.services.OrderService;
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

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private ProductService productService;
    private OrderService orderService;
    private Cart cart;

    @GetMapping
    public String showCartPage(HttpSession session) {
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

    @GetMapping("/order/{id}")
    public String showPlacedOrder(Model model, @PathVariable Long id) {
        Order order = orderService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order doesn't exist"));;
        model.addAttribute("order", order);
        return "/order_details";
    }

    @PostMapping("/order")
    public String placeOrder(@RequestParam String name,
                             @RequestParam String address,
                             @RequestParam(name = "phone_number") String phoneNumber
    ) {
        Order order = new Order();
        order.setName(name);
        order.setAddress(address);
        order.setPhoneNumber(phoneNumber);
        order.setItems(cart.getItems());
        order.setPrice(cart.getPrice());
        orderService.createorSaveOrder(order);
        cart.newCart();     //тут по идее тоже должен быть человеческий метод для решение такой задачи
        return "redirect:/orders";
    }
}
