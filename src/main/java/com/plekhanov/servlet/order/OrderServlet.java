package com.plekhanov.servlet.order;

import com.plekhanov.entity.cart.Cart;
import com.plekhanov.entity.user.User;
import com.plekhanov.entity.order.Order;
import com.plekhanov.service.CartService;
import com.plekhanov.service.OrderService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.plekhanov.constant.listener.ListenerConstants.CONTEXT_CART_SERVICE;
import static com.plekhanov.constant.listener.ListenerConstants.CURRENT_USER;
import static com.plekhanov.constant.Paths.*;

/**
 * servlet that adds {@link Order} to db and clears {@link Cart}
 */
@WebServlet(SLASH + PRODUCT_ORDER_SERVLET)
public class OrderServlet extends HttpServlet {
    /**
     * adds {@link Order} to db and clears {@link Cart}
     *
     * @param req  - {@link HttpServletRequest}
     * @param resp - {@link HttpServletResponse}
     * @throws IOException      - req.getRequestDispatcher.forward() throws
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CartService cartService = (CartService) req.getServletContext().getAttribute(CONTEXT_CART_SERVICE);
        OrderService orderService = new OrderService(req.getServletContext());
        String email = ((User) req.getSession().getAttribute(CURRENT_USER)).getEmail();
        Order order = orderService.createOrder(email);
        orderService.sendOrderToDB(order, cartService.getCart());
        orderService.sendOrderedProductToDB(order.getOrderedProducts());
        cartService.clear();
        resp.sendRedirect(INDEX_PAGE);
    }
}
