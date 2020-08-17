package com.plekhanov.service;

import com.plekhanov.entity.cart.Cart;
import com.plekhanov.entity.cart.OrderCart;
import com.plekhanov.entity.product.Product;
import com.plekhanov.constant.listener.ListenerConstants;
import com.plekhanov.constant.servlet.cart.CartServletConstants;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * service for {@link Cart}
 */
public class CartService {
    private Cart cart;
    private ServletContext servletContext;

    /**
     * @param servletContext - context of site
     */
    public CartService(ServletContext servletContext) {
        this.servletContext = servletContext;
        this.cart = new OrderCart();
    }

    public Cart getCart() {
        return cart;
    }

    /**
     * set cart to attribute
     *
     * @param req - {@link HttpServletRequest }
     */
    public void setCartToAttribute(HttpServletRequest req) {
        req.setAttribute(CartServletConstants.CART, cart);
    }

    /**
     * delete product by id from cart
     *
     * @param id - of product
     */
    public void deleteById(String id) {
        ProductService productService = (ProductService) servletContext.getAttribute(ListenerConstants.CONTEXT_PRODUCT_SERVICE);
        List<Product> products = productService.getCurrentProducts();
        for (Product product : products) {
            if (Integer.parseInt(id) == product.getId()) {
                cart.delete(product);
            }
        }
    }

    /**
     * clears cart
     */
    public void clear() {
        cart.clear();
    }

    /**
     * parse json to cart
     *
     * @param json - string that need parse
     */
    public void parseJson(String json) {
        JSONObject obj = new JSONObject(json);
        ProductService productService = (ProductService) servletContext.getAttribute(ListenerConstants.CONTEXT_PRODUCT_SERVICE);
        List<Product> products = productService.getCurrentProducts();
        for (Product product : products) {
            try {
                int amount = obj.getInt(String.valueOf(product.getId()));
                cart.add(product, amount);
            } catch (JSONException ignored) {
            }
        }
    }
}
