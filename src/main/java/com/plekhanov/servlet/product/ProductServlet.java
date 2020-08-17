package com.plekhanov.servlet.product;

import com.plekhanov.entity.product.Product;
import com.plekhanov.repository.ProductRepository;
import com.plekhanov.service.ProductService;
import com.plekhanov.constant.servlet.product.ProductServletConstants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.plekhanov.constant.listener.ListenerConstants.CONTEXT_PRODUCT_SERVICE;
import static com.plekhanov.constant.Paths.*;

@WebServlet(SLASH + PRODUCT_SERVLET)
public class ProductServlet extends HttpServlet {
    private ProductService productService;

    /**
     * load {@link Product} from db to {@link ProductRepository}
     */
    @Override
    public void init() {
        productService = (ProductService) getServletContext().getAttribute(CONTEXT_PRODUCT_SERVICE);
        productService.loadDAOProducts();
    }

    /**
     * set {@link Product} parameters
     * @param req - {@link HttpServletRequest}
     * @param resp - {@link HttpServletResponse}
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        productService = (ProductService) req.getServletContext().getAttribute(CONTEXT_PRODUCT_SERVICE);
        req.setAttribute(ProductServletConstants.PRODUCTS, productService.getCurrentProducts());
        req.setAttribute(ProductServletConstants.MANUFACTURERS, productService.getManufacturers());
        req.setAttribute(ProductServletConstants.NAMES, productService.getNames());
        req.setAttribute(ProductServletConstants.CATEGORIES, productService.getCategories());
        req.setAttribute(ProductServletConstants.MAX_PRICE, productService.getMaxPrice());
        req.setAttribute(ProductServletConstants.MIN_PRICE, productService.getMinPrice());
        if (req.getSession().getAttribute(ProductServletConstants.NUMBER_PRODUCTS_ON_PAGE) == null) {
            req.getSession().setAttribute(ProductServletConstants.NUMBER_PRODUCTS_ON_PAGE, ProductServletConstants.DEFAULT_NUMBER_ON_PAGE);
        }
    }
}
