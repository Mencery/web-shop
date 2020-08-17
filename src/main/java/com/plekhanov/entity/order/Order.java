package com.plekhanov.entity.order;

import java.sql.Date;
import java.util.List;

/**
 * entity for orders table
 */
public class Order {
    private int id;
    private OrderStatus status;
    private String description;
    private Date date;
    private String userEmail;
    private List<OrderedProduct> orderedProducts;

    public List<OrderedProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(List<OrderedProduct> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = OrderStatus.valueOf(status);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


}
