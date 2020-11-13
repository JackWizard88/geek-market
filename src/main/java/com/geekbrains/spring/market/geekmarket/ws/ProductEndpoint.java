package com.geekbrains.spring.market.geekmarket.ws;

import com.geekbrains.spring.market.geekmarket.services.ProductService;
import com.geekbrains.spring.market.geekmarket.ws.products.GetProductSOAPRequest;
import com.geekbrains.spring.market.geekmarket.ws.products.GetProductSOAPResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.geekbrains.com/market/ws/products";

    private ProductService productService;

    @Autowired
    public ProductEndpoint(ProductService productService) {
        this.productService = productService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductSOAPRequest")
    @ResponsePayload
    public GetProductSOAPResponse getProduct(@RequestPayload GetProductSOAPRequest request) {
        GetProductSOAPResponse response = new GetProductSOAPResponse();
        response.getProduct().addAll(productService.findAllSOAP());
        return response;
    }
}
