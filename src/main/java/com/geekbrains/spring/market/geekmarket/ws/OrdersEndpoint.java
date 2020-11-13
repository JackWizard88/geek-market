package com.geekbrains.spring.market.geekmarket.ws;

import com.geekbrains.spring.market.geekmarket.configs.JwtTokenUtil;
import com.geekbrains.spring.market.geekmarket.entities.User;
import com.geekbrains.spring.market.geekmarket.services.OrderService;
import com.geekbrains.spring.market.geekmarket.services.ProductService;
import com.geekbrains.spring.market.geekmarket.services.UserService;
import com.geekbrains.spring.market.geekmarket.ws.orders.GetOrdersSOAPRequest;
import com.geekbrains.spring.market.geekmarket.ws.orders.GetOrdersSOAPResponse;
import com.geekbrains.spring.market.geekmarket.ws.products.GetProductSOAPRequest;
import com.geekbrains.spring.market.geekmarket.ws.products.GetProductSOAPResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.security.Principal;

@Endpoint
@RequiredArgsConstructor
public class OrdersEndpoint {
    private static final String NAMESPACE_URI = "http://www.geekbrains.com/market/ws/orders";

    private final OrderService orderService;
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOrdersSOAPRequest")
    @ResponsePayload
    public GetOrdersSOAPResponse getProduct(@RequestPayload GetOrdersSOAPRequest request) {
        GetOrdersSOAPResponse response = new GetOrdersSOAPResponse();
        String username = jwtTokenUtil.getUsernameFromToken(request.getToken());
        response.getOrders().addAll(orderService.findByUserSOAP(userService.findByUsername(username)));
        return response;
    }
}
