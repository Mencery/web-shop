package com.plekhanov.filter.locale.strategy.factory;

import com.plekhanov.filter.locale.strategy.CookieStorageStrategy;
import com.plekhanov.filter.locale.strategy.LocaleStorageStrategy;
import com.plekhanov.filter.locale.strategy.SessionStorageStrategy;
import com.plekhanov.filter.locale.strategy.scope.LocaleStrategyScope;

import java.util.HashMap;
import java.util.Map;
/**
 * factory that creates {@link LocaleStorageStrategy}
 */
public class LocaleStrategyFactory {
    private Map<LocaleStrategyScope, LocaleStorageStrategy> map;
    /**
     * initialization map
     */
    public LocaleStrategyFactory() {
        map = new HashMap<>();
        map.put(LocaleStrategyScope.SESSION, new SessionStorageStrategy());
        map.put(LocaleStrategyScope.COOKIE, new CookieStorageStrategy());
    }
    /**
     * obtain  {@link LocaleStorageStrategy}  by strategy scope
     * @param strategyScope - key of {@link LocaleStorageStrategy}
     * @return  {@link LocaleStorageStrategy}
     */
    public LocaleStorageStrategy getStrategy(LocaleStrategyScope strategyScope) {
        return map.get(strategyScope);
    }
}
