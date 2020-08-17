package com.plekhanov.entity.user;

import java.util.HashMap;
import java.util.Map;

public enum Role {
    ADMIN(1), USER(2);
    private int value;
    private static Map<Integer, Role> map = new HashMap<>();

    /**
     *
     * @param value - map value of role
     */
    Role(int value) {
        this.value = value;
    }

    static {
        for (Role role : Role.values()) {
            map.put(role.value, role);
        }
    }

    /**
     *
     * @param role - int value of role
     * @return product category
     */
    public static Role valueOf(int role) {
        return map.get(role);
    }

    /**
     *
     * @return int value of role
     */
    public int getValue() {
        return value;
    }
}
