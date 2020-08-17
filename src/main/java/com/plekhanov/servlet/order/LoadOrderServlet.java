package com.plekhanov.servlet.order;

import com.plekhanov.service.CartService;
import com.plekhanov.entity.cart.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.plekhanov.constant.listener.ListenerConstants.CONTEXT_CART_SERVICE;
import static com.plekhanov.constant.Paths.*;

/**
 * servlet that set up {@link Cart} parameters before forward to order.jsp
 */
@WebServlet(SLASH + PRODUCT_LOAD_ORDER_SERVLET)
public class LoadOrderServlet  extends HttpServlet {
    /**
     * set up {@link Cart} parameters and forward to order.jsp
     *
     * @param req  - {@link HttpServletRequest}
     * @param resp - {@link HttpServletResponse}
     * @throws ServletException - req.getRequestDispatcher.forward() throws
     * @throws IOException      - req.getRequestDispatcher.forward() throws
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartService cartService = (CartService) req.getServletContext().getAttribute(CONTEXT_CART_SERVICE);
        cartService.setCartToAttribute(req);
        req.getRequestDispatcher(ORDER_PAGE).forward(req, resp);
    }
}
