package com.plekhanov.servlet.cart;

import com.plekhanov.service.CartService;
import com.plekhanov.constant.Paths;
import com.plekhanov.constant.listener.ListenerConstants;
import com.plekhanov.entity.cart.Cart;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * servlet that clear {@link Cart}
 */
@WebServlet(Paths.SLASH + Paths.PRODUCT_CART_CLEAR_SERVLET)
public class ClearCartServlet extends HttpServlet {
    /**
     * clear {@link Cart}
     *
     * @param req  - {@link HttpServletRequest}
     * @param resp - {@link HttpServletResponse}
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp){
        CartService cartService = (CartService) req.getServletContext().getAttribute(ListenerConstants.CONTEXT_CART_SERVICE);
        cartService.clear();
        cartService.setCartToAttribute(req);
    }
}
