package com.plekhanov.entity.order;

import java.math.BigDecimal;

/**
 * entity for ordered_products table
 */
public class OrderedProduct {
 private  int productId;
 private int orderId;
 private BigDecimal price;
 private int amount;

    /**
     *
     * @param productId - id of product
     * @param orderId - id of order
     * @param price - price of ordered product
     * @param amount - of products
     */
    public OrderedProduct(int productId, int orderId, BigDecimal price, int amount) {
        this.productId = productId;
        this.orderId = orderId;
        this.price = price;
        this.amount = amount;
    }

    public int getProductId() {
        return productId;
    }

    public int getOrderId() {
        return orderId;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }
}
