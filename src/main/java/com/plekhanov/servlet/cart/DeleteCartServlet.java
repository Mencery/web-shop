package com.plekhanov.servlet.cart;

import com.plekhanov.service.CartService;
import com.plekhanov.constant.Paths;
import com.plekhanov.constant.listener.ListenerConstants;
import com.plekhanov.entity.cart.Cart;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.plekhanov.constant.servlet.cart.CartServletConstants.DELETE_PRODUCT_ID;

/**
 * servlet that delete product by id from  {@link Cart}
 */
@WebServlet(Paths.SLASH+ Paths.PRODUCT_CART_DELETE_SERVLET)
public class DeleteCartServlet extends HttpServlet {
    /**
     * delete product in {@link Cart} by id
     *
     * @param req  - {@link HttpServletRequest}
     * @param resp - {@link HttpServletResponse}
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        CartService cartService = (CartService) req.getServletContext().getAttribute(ListenerConstants.CONTEXT_CART_SERVICE);
        if (req.getParameter(DELETE_PRODUCT_ID) != null) {
            cartService.deleteById(req.getParameter(DELETE_PRODUCT_ID));
        }
        cartService.setCartToAttribute(req);
    }
}
