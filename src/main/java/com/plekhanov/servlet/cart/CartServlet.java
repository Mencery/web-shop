package com.plekhanov.servlet.cart;

import com.plekhanov.service.CartService;
import com.plekhanov.entity.cart.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.plekhanov.constant.servlet.cart.CartServletConstants.PAGE_CART;
import static com.plekhanov.constant.listener.ListenerConstants.CONTEXT_CART_SERVICE;
import static com.plekhanov.constant.Paths.*;

/**
 * servlet that handles {@link Cart}
 */
@WebServlet(SLASH + PRODUCT_CART_SERVLET)
public class CartServlet extends HttpServlet {
    /**
     * fills cart by page parameters
     *
     * @param req  - {@link HttpServletRequest}
     * @param resp - {@link HttpServletResponse}
     * @throws ServletException - req.getRequestDispatcher.forward() throws
     * @throws IOException      - req.getRequestDispatcher.forward() throws
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartService cartService = (CartService) req.getServletContext().getAttribute(CONTEXT_CART_SERVICE);
        if (req.getParameter(PAGE_CART) != null) {
            cartService.parseJson(req.getParameter(PAGE_CART));
        }
        cartService.setCartToAttribute(req);
        req.getRequestDispatcher(CART_PAGE).forward(req, resp);
    }
}
