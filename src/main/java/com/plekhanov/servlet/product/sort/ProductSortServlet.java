package com.plekhanov.servlet.product.sort;

import com.plekhanov.entity.product.Product;
import com.plekhanov.service.ProductService;
import com.plekhanov.constant.servlet.product.ProductServletConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static com.plekhanov.constant.listener.ListenerConstants.CONTEXT_PRODUCT_SERVICE;
import static com.plekhanov.constant.Paths.*;

/**
 * servlet sorts {@link Product}
 */
@WebServlet(urlPatterns = SLASH + PRODUCT_SORT_SERVLET)
public class ProductSortServlet extends HttpServlet {
    private Map<String, Comparator<Product>> comparatorMap;

    /**
     * initialization comparatorMap
     */
    @Override
    public void init() {
        comparatorMap = new HashMap<>();
        comparatorMap.put(ProductServletConstants.COMPARATOR_MODE_NAME_FROM_A_Z, Comparator.comparing(Product::getName));
        comparatorMap.put(ProductServletConstants.COMPARATOR_MODE_NAME_FROM_Z_A, Comparator.comparing(Product::getName).reversed());
        comparatorMap.put(ProductServletConstants.COMPARATOR_MODE_PRICE_LESS_MORE, Comparator.comparing(Product::getPrice));
        comparatorMap.put(ProductServletConstants.COMPARATOR_MODE_PRICE_MORE_LESS, Comparator.comparing(Product::getPrice).reversed());
    }

    /**
     * sorts {@link Product}
     *
     * @param req  - {@link HttpServletRequest}
     * @param resp - {@link HttpServletResponse}
     * @throws ServletException -  req.getRequestDispatcher throws {@link ServletException}
     * @throws IOException      - req.getRequestDispatcher throws {@link ServletException}
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = (ProductService) req.getServletContext().getAttribute(CONTEXT_PRODUCT_SERVICE);
        String mode;
        if ((mode = req.getParameter(ProductServletConstants.SORT_MODE)) != null) {
            productService.sortProducts(comparatorMap.get(mode));
        }
        req.getRequestDispatcher(INDEX_PAGE).forward(req, resp);
    }
}
