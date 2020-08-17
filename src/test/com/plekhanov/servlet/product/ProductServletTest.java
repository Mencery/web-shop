package com.plekhanov.servlet.product;

import com.plekhanov.db.ProductDAO;
import com.plekhanov.entity.product.Product;
import com.plekhanov.service.ProductService;
import com.plekhanov.constant.listener.ListenerConstants;
import com.plekhanov.constant.servlet.product.ProductServletConstants;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

public class ProductServletTest {

    private ProductServlet productServlet;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletContext servletContext;
    @Mock
    private HttpSession session;
    private ProductService productService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        productServlet = new ProductServlet();
        productService = new ProductService(servletContext);
        when(request.getServletContext()).thenReturn(servletContext);
        ProductDAO dao = new ProductDAO();
        when(servletContext.getAttribute(ListenerConstants.CONTEXT_PRODUCTS_DAO)).thenReturn(dao);
        productService.loadDAOProducts();
    }

    @Test
    public void shouldSetAttributes() {
        when(servletContext.getAttribute(ListenerConstants.CONTEXT_PRODUCT_SERVICE)).thenReturn(productService);
        when(request.getAttribute(ProductServletConstants.PRODUCTS)).thenReturn(new ArrayList<Product>());
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ProductServletConstants.NUMBER_PRODUCTS_ON_PAGE)).thenReturn(new Object());
        productServlet.doGet(request, response);
        verify(request).setAttribute(eq(ProductServletConstants.PRODUCTS), anyCollectionOf(Product.class));
    }
}