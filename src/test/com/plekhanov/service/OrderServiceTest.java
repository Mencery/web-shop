package com.plekhanov.service;

import com.plekhanov.db.OrderDAO;
import com.plekhanov.db.OrderedProductDAO;
import com.plekhanov.entity.cart.Cart;
import com.plekhanov.entity.cart.OrderCart;
import com.plekhanov.entity.order.Order;
import com.plekhanov.entity.order.OrderStatus;
import com.plekhanov.entity.product.Product;
import com.plekhanov.constant.listener.ListenerConstants;
import org.apache.derby.client.am.SqlException;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletContext;

import java.math.BigDecimal;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {
    private OrderService orderService;
    private OrderDAO orderDao;
    private OrderedProductDAO orderedProductDAO;

    @Before
    public void setUp() {
        ServletContext servletContext = mock(ServletContext.class);
        orderService = new OrderService(servletContext);
        orderDao = mock(OrderDAO.class);
        orderedProductDAO = mock(OrderedProductDAO.class);
        when(servletContext.getAttribute(ListenerConstants.CONTEXT_ORDER_DAO)).thenReturn(orderDao);
        when(servletContext.getAttribute(ListenerConstants.CONTEXT_ORDERED_PRODUCT_DAO)).thenReturn(orderedProductDAO);

    }

    @Test
    public void shouldCreateOrder() {
        //given
        String email = "email";
        //when
        Order order = orderService.createOrder(email);
        //then
        assertEquals(email, order.getUserEmail());
        assertEquals(OrderStatus.FORMED, order.getStatus());
        assertNotNull(order.getDate());
    }

    @Test
    public void shouldSendsOrderToDB_whenNoSQLException() throws SQLException {
        //given
        String email = "email";
        Order order = orderService.createOrder(email);
        Cart cart = new OrderCart();
        cart.add(createProduct(), 1);
        cart.add(createProduct(), 2);
        //when
        orderService.sendOrderToDB(order, cart);
        //then
        verify(orderDao).add(any(Order.class));
    }

    @SuppressWarnings("unchecked")
    @Test(expected = SqlException.class)
    public void shouldDoesntSendOrderToDB_whenSQLException() throws SQLException {
        //given
        String email = "email";
        Order order = orderService.createOrder(email);
        Cart cart = new OrderCart();
        when(orderDao.add(order)).thenThrow(SqlException.class);

        //when
        orderService.sendOrderToDB(order, cart);
    }

    @Test
    public void shouldSendsOrderedProductsToDB_whenNoSQLException() throws SQLException {
        //given
        String email = "email";
        Order order = orderService.createOrder(email);
        Cart cart = new OrderCart();
        cart.add(createProduct(), 1);
        cart.add(createProduct(), 2);
        //when
        orderService.sendOrderToDB(order, cart);
        orderService.sendOrderedProductToDB(order.getOrderedProducts());
        //then
        verify(orderDao).add(any(Order.class));
        verify(orderedProductDAO).addAll(any());
    }

    private Product createProduct() {
        Product product = new Product();
        product.setPrice(new BigDecimal(5));
        return product;
    }
}