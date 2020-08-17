package com.plekhanov.servlet.cart;

import com.plekhanov.service.CartService;
import com.plekhanov.constant.listener.ListenerConstants;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.when;

public class ClearCartServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletContext servletContext;

    private ClearCartServlet servlet;
    private CartService cartService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cartService = new CartService(servletContext);
        servlet = new ClearCartServlet();
    }

    @Test
    public void shouldExecutesClear(){
        //given
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute(ListenerConstants.CONTEXT_CART_SERVICE)).thenReturn(cartService);
        //when
        servlet.doGet(request, response);
    }
}