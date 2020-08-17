package com.plekhanov.entity.product;

import java.util.HashMap;
import java.util.Map;

/**
 * enum for {@link Product} category
 */
public enum ProductCategory {
    ELECTRONIC(1), DEVICE(2),HOUSEHOLD(3), FOOD(4), MILK(5);
    private int value;
    private static Map<Integer,ProductCategory> map = new HashMap<>();

    /**
     *
     * @param value - map value of category
     */
     ProductCategory(int value) {
        this.value = value;
    }

    static {
        for (ProductCategory productCategory : ProductCategory.values()) {
            map.put(productCategory.value, productCategory);
        }
    }

    /**
     *
     * @param productCategory - int value of category
     * @return product category
     */
    public static ProductCategory valueOf(int productCategory) {
        return map.get(productCategory);
    }

    /**
     *
     * @return int value of category
     */
    public int getValue() {
        return value;
    }
}
