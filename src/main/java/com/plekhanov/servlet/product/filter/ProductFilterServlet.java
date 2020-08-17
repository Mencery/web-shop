package com.plekhanov.servlet.product.filter;

import com.plekhanov.entity.product.Product;
import com.plekhanov.service.ProductService;
import com.plekhanov.servlet.Validator;
import com.plekhanov.constant.servlet.product.ProductServletConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.plekhanov.constant.listener.ListenerConstants.CONTEXT_PRODUCT_SERVICE;
import static com.plekhanov.constant.Paths.*;

/**
 * servlet filters {@link Product}
 */
@WebServlet(SLASH + PRODUCT_FILTER_SERVLET)
public class ProductFilterServlet extends HttpServlet {
    private ProductService productService;

    /**
     * Filters product and forward to index.jsp
     * @param req - {@link HttpServletRequest}
     * @param resp - {@link HttpServletResponse}
     * @throws ServletException -  req.getRequestDispatcher throws {@link ServletException}
     * @throws IOException - req.getRequestDispatcher throws {@link ServletException}
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        productService = (ProductService) req.getServletContext().getAttribute(CONTEXT_PRODUCT_SERVICE);
        int[] price = getPriceRange(req);
        productService.filterProducts(
                productService.filterProductByManufacturers(getParameters(req, ProductServletConstants.CHECK_BOX_MANUFACTURERS))
                        .and(productService.filterProductByCategories(getParameters(req, ProductServletConstants.CHECK_BOX_CATEGORIES)))
                        .and(productService.filterProductByNames(getParameters(req, ProductServletConstants.CHECK_BOX_NAMES)))
                        .and(productService.filterProductByPriceRange(price[0], price[1]))
        );
        req.getRequestDispatcher(INDEX_PAGE).forward(req, resp);
    }

    /**
     *
     * @param request - {@link HttpServletRequest}
     * @param groupName - name of group checkboxes
     * @return clicked options
     */
    private List<String> getParameters(HttpServletRequest request, String groupName) {
        List<String> parameters = new ArrayList<>();
        String[] p = request.getParameterValues(groupName);
        if (p != null) {
            parameters.addAll(Arrays.asList(p));
        }
        return parameters;
    }

    /**
     *
     * @param req - {@link HttpServletRequest}
     * @return input price range
     */
    private int[] getPriceRange(HttpServletRequest req) {
        int productMax = productService.getMaxPrice();
        int productMin = productService.getMinPrice();
        String inputMax = req.getParameter(ProductServletConstants.MAX_INPUT);
        String inputMin = req.getParameter(ProductServletConstants.MIN_INPUT);
        int max = productMax;
        int min = productMin;

        if (Validator.checkInputMinMax(inputMax, productMin, productMax)) {
            max = Integer.parseInt(inputMax);
        }
        if (Validator.checkInputMinMax(inputMin, productMin, productMax)) {
            min = Integer.parseInt(inputMin);
        }
        if (!Validator.compareMinMax(min, max)) {
            req.setAttribute(ProductServletConstants.RANGE_ERROR, ProductServletConstants.MIN_CANNOT_BE_MORE_THEN_MAX);
            return new int[]{productMin, productMax};
        }
        return new int[]{min, max};
    }
}
