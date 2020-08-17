package com.plekhanov.servlet.product.grid;

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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductGridServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletContext servletContext;
    @Mock
    private RequestDispatcher requestDispatcher;

    private ProductGridServlet servlet;
    private ProductService productService;
    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        productService = new ProductService(servletContext);
        servlet = new ProductGridServlet();
    }

    @Test
    public void shouldForwardToIndexPage() throws ServletException, IOException {
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute(ListenerConstants.CONTEXT_PRODUCT_SERVICE)).thenReturn(productService);
        when(request.getRequestDispatcher(Paths.INDEX_PAGE)).thenReturn(requestDispatcher);
        servlet.doGet(request, response);
        verify(requestDispatcher).forward(request, response);
    }
}