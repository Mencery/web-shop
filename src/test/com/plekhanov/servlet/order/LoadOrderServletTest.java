package com.plekhanov.servlet.order;

import com.plekhanov.service.CartService;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoadOrderServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletContext servletContext;
    @Mock
    private RequestDispatcher requestDispatcher;

    private LoadOrderServlet servlet;
    private CartService cartService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cartService = new CartService(servletContext);
        servlet = new LoadOrderServlet();
    }

    @Test
    public void shouldForwardToOrderPage() throws ServletException, IOException {
        //given
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute(ListenerConstants.CONTEXT_CART_SERVICE)).thenReturn(cartService);
        when(request.getRequestDispatcher(Paths.ORDER_PAGE)).thenReturn(requestDispatcher);
        //when
        servlet.doGet(request, response);
        //then
        verify(requestDispatcher).forward(request, response);
    }
}