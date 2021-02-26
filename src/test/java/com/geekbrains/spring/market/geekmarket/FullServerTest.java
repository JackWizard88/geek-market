package com.geekbrains.spring.market.geekmarket;


import com.geekbrains.spring.market.geekmarket.dto.OrderDto;
import com.geekbrains.spring.market.geekmarket.entities.Category;
import com.geekbrains.spring.market.geekmarket.entities.Product;
import com.geekbrains.spring.market.geekmarket.utils.PageImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class FullServerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void CategoriesControllerTest() {
        List<Category> categories = restTemplate.getForObject("/api/v1/categories", List.class);
        assertThat(categories).isNotNull();
        assertThat(categories).isNotEmpty();
    }

    @Test
    public void ProductsControllerTest() {
        PageImpl<Product> products = restTemplate.getForObject("/api/v1/products", PageImpl.class);  //тут не собирался PageImpl изза того что я затер дефаултный конструктор
        assertThat(products.getContent()).isNotNull();
        assertThat(products.getContent()).isNotEmpty();
        Assertions.assertEquals(0, products.getNumber());
    }

    @Test
//    @WithMockUser(username = "user", roles = "USER")
    public void OrderControllerTest() {
        System.out.println(restTemplate.withBasicAuth("user", "100").getForObject("/api/v1/orders", String.class));
        List<OrderDto> orders = restTemplate.withBasicAuth("user", "100").getForObject("/api/v1/orders", List.class);
        assertThat(orders).isNotNull();
        assertThat(orders).isNotEmpty();
    }
    //c @WithMockUser(username = "user", roles = "USER") тест не проходит потому что ответ от сервера такой:
    //{"timestamp":"2020-12-04T08:00:48.654+00:00","status":403,"error":"Forbidden","message":"","path":"/market/api/v1/orders"}

    // с withBasicAuth("user", "100") ответ от сервера тот же

    // закомментил соответствующую строку в секьюрити, это ничего не изменило

}
