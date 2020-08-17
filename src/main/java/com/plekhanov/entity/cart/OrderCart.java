package com.plekhanov.entity.cart;

import com.plekhanov.entity.product.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * wrapper for cart map
 */
public class OrderCart implements Cart {
    private Map<Product, Integer> cart;

    /**
     * initialisation cart map
     */
    public OrderCart() {
        this.cart = new HashMap<>();
    }

    /**
     * add product to cart
     *
     * @param product - product that add
     */
    @Override
    public void add(Product product) {
        if (cart.containsKey(product)) {
            cart.put(product, cart.get(product) + 1);
        } else {
            cart.put(product, 1);
        }
    }

    /**
     * add product to cart
     *
     * @param product - product that add
     * @param amount  - of product
     */
    public void add(Product product, int amount) {
        cart.put(product, amount);
    }

    /**
     * delete product from cart
     *
     * @param product - product that delete
     */
    @Override
    public void delete(Product product) {
        cart.remove(product);
    }

    /**
     * clear cart
     */
    @Override
    public void clear() {
        cart.clear();
    }

    /**
     * @return set of products
     */
    @Override
    public Set<Product> getProducts() {
        return cart.keySet();
    }

    /**
     * @param product - that contains in map
     * @return amount of product
     */
    @Override
    public int getAmount(Product product) {
        return cart.get(product);
    }

    /**
     * @return price of product
     */
    public BigDecimal getPrice(Product product) {
        int amount = cart.get(product);
        return product.getPrice().multiply(new BigDecimal(amount));
    }

    /**
     * @return total price
     */
    @Override
    public BigDecimal getTotalPrice() {
        BigDecimal total = new BigDecimal(0);
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            Product k = entry.getKey();
            Integer v = entry.getValue();
            total = total.add(getPrice(k));
        }
        return total;
    }
}
