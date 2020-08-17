package com.plekhanov.service;

import com.plekhanov.db.ProductDAO;
import com.plekhanov.constant.listener.ListenerConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletContext;

import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceTest {
    private ProductService productService;

    private ServletContext servletContext;

    @Before
    public void setUp()  {
        servletContext = mock(ServletContext.class);
        productService = new ProductService(servletContext);
    }

    @Test
    public void shouldLoadProducts() {
        ProductDAO dao = new ProductDAO();
        when(servletContext.getAttribute(ListenerConstants.CONTEXT_PRODUCTS_DAO)).thenReturn(dao);
        productService.loadDAOProducts();
        Assert.assertNotNull(productService.getCurrentProducts());
    }

    @Test
    public void shouldFilterProducts() {
        ProductDAO dao = new ProductDAO();
        when(servletContext.getAttribute(ListenerConstants.CONTEXT_PRODUCTS_DAO)).thenReturn(dao);
        productService.loadDAOProducts();
        productService.filterProducts(product -> product.getId() == 1);
        Assert.assertEquals(1,productService.getCurrentProducts().size());
    }
    @Test
    public void shouldReturnNamesSetProducts() {
        ProductDAO dao = new ProductDAO();
        when(servletContext.getAttribute(ListenerConstants.CONTEXT_PRODUCTS_DAO)).thenReturn(dao);
        productService.loadDAOProducts();

        Set<String> names= productService.getNames();
        Assert.assertNotNull(names);
    }
    @Test
    public void shouldReturnManufacturersSetProducts() {
        ProductDAO dao = new ProductDAO();
        when(servletContext.getAttribute(ListenerConstants.CONTEXT_PRODUCTS_DAO)).thenReturn(dao);
        productService.loadDAOProducts();

        Set<String> manufacturers= productService.getManufacturers();
        Assert.assertNotNull(manufacturers);
    }
    @Test
    public void shouldReturnCategoriesSetProducts() {
        ProductDAO dao = new ProductDAO();
        when(servletContext.getAttribute(ListenerConstants.CONTEXT_PRODUCTS_DAO)).thenReturn(dao);
        productService.loadDAOProducts();
        Set<String> categories = productService.getCategories();
        Assert.assertNotNull(categories);
    }
}