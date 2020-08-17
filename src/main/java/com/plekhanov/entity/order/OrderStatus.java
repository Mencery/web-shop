package com.plekhanov.entity.order;



import java.util.HashMap;
import java.util.Map;
/**
 * enum for {@link Order} status
 */
public enum  OrderStatus {
    APPLY(1), REFUSE(2), CONFIRM(3),
    FORMED(4), SENT(5), COMPLETED(6);
    private int value;
    private static Map<Integer,OrderStatus > map = new HashMap<>();

    /**
     *
     * @param value - map value of status
     */
     OrderStatus(int value) {
        this.value = value;
    }


    static {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            map.put(orderStatus.value, orderStatus);
        }
    }

    /**
     *
     * @param orderStatus - int value of status
     * @return product category
     */
    public static OrderStatus valueOf(int orderStatus) {
        return  map.get(orderStatus);
    }

    /**
     *
     * @return int value of category
     */
    public int getValue() {
        return value;
    }
}
