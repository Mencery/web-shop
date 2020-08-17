package com.plekhanov.service;

import com.plekhanov.entity.product.Product;
import com.plekhanov.constant.listener.ListenerConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CartServiceTest {
    private CartService cartService;

    @Before
    public void setUp()  {
        ServletContext servletContext = mock(ServletContext.class);
        cartService = new CartService(servletContext);
        ProductService productService = mock(ProductService.class);
        when(servletContext.getAttribute(ListenerConstants.CONTEXT_PRODUCT_SERVICE)).thenReturn(productService);
        when(productService.getCurrentProducts()).thenReturn(fillProductList());
    }

    @Test
    public void shouldParsesJSON_whenJsonCorrect() {
        //given
        String json = "{\"1\":2,\"2\":2}";
        int expected = 2;
        //when
        cartService.parseJson(json);
        //then
        Assert.assertEquals(expected, cartService.getCart().getProducts().size());
    }
    @Test
    public void shouldDoesntParseJSON_whenJsonIncorrect() {
        //given
        String json = "{44:2,3:2}";
        int expected = 0;
        //when
        cartService.parseJson(json);
        //then
        Assert.assertEquals(expected, cartService.getCart().getProducts().size());
    }

    @Test
    public void shouldDeletesProductById_whenIdGiven() {
        //given
        String json = "{\"1\":2,\"2\":2}";
        int expected = 2;
        //when
        cartService.parseJson(json);
        //then
        Assert.assertEquals(expected, cartService.getCart().getProducts().size());
        //when
        cartService.deleteById("1");
        expected =1;
        //then
        Assert.assertEquals(expected, cartService.getCart().getProducts().size());
    }
    @Test
    public void shouldDeletesProductById_whenIdGivenIncorrect() {
        //given
        String json = "{\"1\":2,\"2\":2}";
        int expected = 2;
        //when
        cartService.parseJson(json);
        //then
        Assert.assertEquals(expected, cartService.getCart().getProducts().size());
        //when
        cartService.deleteById("4");
        expected =2;
        //then
        Assert.assertEquals(expected, cartService.getCart().getProducts().size());
    }
    @Test
    public void shouldClearCart() {
        //given
        String json = "{\"1\":2,\"2\":2}";
        int expected = 2;
        //when
        cartService.parseJson(json);
        //then
        Assert.assertEquals(expected, cartService.getCart().getProducts().size());
        //when
        cartService.clear();
        expected =0;
        //then
        Assert.assertEquals(expected, cartService.getCart().getProducts().size());
    }
    private List<Product> fillProductList() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(1);
        products.add(product1);
        Product product2 = new Product();
        product2.setId(2);
        products.add(product2);
        return products;
    }
}