package com.plekhanov.servlet.order;

import com.plekhanov.db.OrderDAO;
import com.plekhanov.db.OrderedProductDAO;
import com.plekhanov.entity.user.User;
import com.plekhanov.service.CartService;
import com.plekhanov.constant.listener.ListenerConstants;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletContext servletContext;
    @Mock
    private HttpSession session;
    @Mock
    private OrderDAO orderDao;
    @Mock
    private OrderedProductDAO orderedProductDAO;
    private OrderServlet servlet;
    private CartService cartService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cartService = new CartService(servletContext);
        servlet = new OrderServlet();
        when(request.getSession()).thenReturn(session);
        when(servletContext.getAttribute(ListenerConstants.CONTEXT_ORDER_DAO)).thenReturn(orderDao);
        when(servletContext.getAttribute(ListenerConstants.CONTEXT_ORDERED_PRODUCT_DAO)).thenReturn(orderedProductDAO);

    }

    @Test
    public void shouldForwardToOrderPage() throws IOException, SQLException {
        //given
        User user = new User();
        user.setEmail("email");
        when(session.getAttribute(ListenerConstants.CURRENT_USER)).thenReturn(user);
        when( orderDao.add(any())).thenReturn(1);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute(ListenerConstants.CONTEXT_CART_SERVICE)).thenReturn(cartService);
        //when
        servlet.doPost(request, response);
        //then
        verify(orderedProductDAO).addAll(any());
        verify(response).sendRedirect(anyString());
    }
}