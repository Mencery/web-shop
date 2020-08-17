package com.plekhanov.servlet.product.grid;

import com.plekhanov.entity.product.Product;
import com.plekhanov.servlet.Validator;
import com.plekhanov.constant.servlet.product.ProductServletConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.plekhanov.constant.Paths.*;

/**
 * servlet set input number {@link Product} on page
 */
@WebServlet(SLASH + PRODUCT_GRID_SERVLET)
public class ProductGridServlet extends HttpServlet {
    /**
     * set input number {@link Product} on page
     *
     * @param req  - {@link HttpServletRequest}
     * @param resp - {@link HttpServletResponse}
     * @throws ServletException -  req.getRequestDispatcher throws {@link ServletException}
     * @throws IOException      - req.getRequestDispatcher throws {@link ServletException}
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Validator.checkRawAndColNumbers(req.getParameter(ProductServletConstants.NUMBER_PRODUCTS_ON_PAGE))) {
            req.getSession().setAttribute(
                    ProductServletConstants.NUMBER_PRODUCTS_ON_PAGE, Integer.parseInt(req.getParameter(ProductServletConstants.NUMBER_PRODUCTS_ON_PAGE)));
        }
        req.getRequestDispatcher(INDEX_PAGE).forward(req, resp);
    }
}
