package com.plekhanov.repository;

import com.plekhanov.entity.order.Order;

import java.util.List;

public class OrderRepository {
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
