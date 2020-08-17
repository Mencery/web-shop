package com.plekhanov.servlet.cart;

import com.plekhanov.entity.product.Product;
import com.plekhanov.service.CartService;
import com.plekhanov.service.ProductService;
import com.plekhanov.constant.Paths;
import com.plekhanov.constant.listener.ListenerConstants;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.plekhanov.constant.servlet.cart.CartServletConstants.PAGE_CART;
import static org.mockito.Mockito.*;

public class CartServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletContext servletContext;
    @Mock
    private RequestDispatcher requestDispatcher;

    private CartServlet servlet;
    private CartService cartService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cartService = new CartService(servletContext);
        servlet = new CartServlet();
    }

    @Test
    public void shouldForwardToCartPage_whenHasntGivenCartParameter() throws ServletException, IOException {
        //given
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute(ListenerConstants.CONTEXT_CART_SERVICE)).thenReturn(cartService);
        when(request.getRequestDispatcher(Paths.CART_PAGE)).thenReturn(requestDispatcher);
        //when
        servlet.doGet(request, response);
        //then
        verify(requestDispatcher).forward(request, response);
    }
    @Test
    public void shouldForwardToCartPage_whenHasGivenCartParameter() throws ServletException, IOException {
        //given
        when(request.getServletContext()).thenReturn(servletContext);
        String json = "{\"1\":2,\"2\":2}";
        when(request.getParameter(PAGE_CART)).thenReturn(json);
        ProductService productService = mock(ProductService.class);
        when(servletContext.getAttribute(ListenerConstants.CONTEXT_PRODUCT_SERVICE)).thenReturn(productService);
        when(productService.getCurrentProducts()).thenReturn(fillProductList());
        when(servletContext.getAttribute(ListenerConstants.CONTEXT_CART_SERVICE)).thenReturn(cartService);
        when(request.getRequestDispatcher(Paths.CART_PAGE)).thenReturn(requestDispatcher);
        //when
        servlet.doGet(request, response);
        //then
        verify(requestDispatcher).forward(request, response);
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