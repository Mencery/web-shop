package com.plekhanov.repository;

import com.plekhanov.entity.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * repository for {@link Product}
 */
public class ProductRepository {
    private List<Product> products;
    private List<Product> currentProducts;

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setCurrentProducts(List<Product> products) {
        this.currentProducts = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Product> getCurrentProducts() {
        return currentProducts;
    }

    /**
     *filter {@link Product} by {@link Predicate<Product>}
     * @param predicate - filtering by this predicate
     * @return filtered Products
     */
    public List<Product> filterProducts(Predicate<Product> predicate) {
        List<Product> result = new ArrayList<>();
        products.stream().filter(predicate).forEach(result::add);
        return result;
    }
}
