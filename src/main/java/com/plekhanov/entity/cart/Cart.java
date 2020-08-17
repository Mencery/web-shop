package com.plekhanov.entity.cart;

import com.plekhanov.entity.product.Product;

import java.math.BigDecimal;
import java.util.Set;

/**
 * interface for cart
 */
public interface Cart {
    /**
     *add product to cart
     * @param product - product that add
     */
    void add(Product product);

    /**
     * add product to cart
     * @param product - product that add
     * @param amount - of product
     */
    void add(Product product, int amount);

    /**
     * delete product from cart
     * @param product - product that delete
     */
    void delete(Product product);
    /**
     * clear cart
     */
    void clear();
    /**
     *
     * @return set of products
     */
    Set<Product> getProducts();

    /**
     *
     * @param product - that contains in map
     * @return amount of product
     */
    int getAmount(Product product);
    /**
     *
     * @return  price of product
     */
    BigDecimal getPrice(Product product);
    /**
     *
     * @return total price
     */
    BigDecimal getTotalPrice();
}
