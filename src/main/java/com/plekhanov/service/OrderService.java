package com.plekhanov.service;

import com.plekhanov.db.OrderDAO;
import com.plekhanov.db.OrderedProductDAO;
import com.plekhanov.entity.cart.Cart;
import com.plekhanov.entity.order.Order;
import com.plekhanov.entity.order.OrderStatus;
import com.plekhanov.entity.order.OrderedProduct;

import javax.servlet.ServletContext;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.plekhanov.constant.listener.ListenerConstants.CONTEXT_ORDERED_PRODUCT_DAO;
import static com.plekhanov.constant.listener.ListenerConstants.CONTEXT_ORDER_DAO;

/**
 * service for {@link Order}
 */
public class OrderService {
    private ServletContext servletContext;

    /**
     * @param servletContext - context of site
     */
    public OrderService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /**
     * create  {@link Order}
     *
     * @param email - user email
     * @return {@link Order}
     */
    public Order createOrder(String email) {
        Order order = new Order();
        order.setStatus(OrderStatus.FORMED.getValue());
        order.setDate(new Date(Calendar.getInstance().getTime().getTime()));
        order.setUserEmail(email);

        return order;
    }

    /**
     * sends {@link Order} to DB
     *
     * @param order - {@link Order}
     * @param cart  - {@link Cart}
     */
    public void sendOrderToDB(Order order, Cart cart) {
        try {
            OrderDAO dao = (OrderDAO) servletContext.getAttribute(CONTEXT_ORDER_DAO);
            int id = dao.add(order);
            order.setId(id);
            order.setOrderedProducts(fillOrderedProduct(id, cart));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * sends list of {@link OrderedProduct } to DB
     *
     * @param orderedProducts -list of {@link OrderedProduct}
     */
    public void sendOrderedProductToDB(List<OrderedProduct> orderedProducts) {
        try {
            OrderedProductDAO dao = (OrderedProductDAO) servletContext.getAttribute(CONTEXT_ORDERED_PRODUCT_DAO);
            dao.addAll(orderedProducts);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param orderId - id of {@link Order}
     * @param cart - {@link Cart}
     * @return filled list of {@link OrderedProduct}
     */
    private List<OrderedProduct> fillOrderedProduct(int orderId, Cart cart) {
        List<OrderedProduct> orderedProducts = new ArrayList<>();
        cart.getProducts().forEach(product -> orderedProducts
                .add(new OrderedProduct(product.getId(), orderId, cart.getPrice(product), cart.getAmount(product))));
        return orderedProducts;
    }
}
