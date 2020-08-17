package com.plekhanov.servlet.captcha.strategy.factory;

import com.plekhanov.entity.user.User;
import com.plekhanov.servlet.captcha.strategy.CaptchaStrategy;
import com.plekhanov.servlet.captcha.strategy.CookieCaptchaStrategy;
import com.plekhanov.servlet.captcha.strategy.HiddenFieldCaptchaStrategy;
import com.plekhanov.servlet.captcha.strategy.SessionCaptchaStrategy;
import com.plekhanov.servlet.captcha.strategy.scope.CaptchaStrategyScope;

import java.util.HashMap;
import java.util.Map;

/**
 * factory that creates {@link CaptchaStrategy}
 */
public class CaptchaStrategyFactory {
    private Map<CaptchaStrategyScope, CaptchaStrategy> captchaStrategyMap;

    public User user;

    /**
     * initialization captchaStrategyMap
     */
    public CaptchaStrategyFactory() {
        captchaStrategyMap = new HashMap<>();

        captchaStrategyMap.put(CaptchaStrategyScope.SESSION, new SessionCaptchaStrategy());
        captchaStrategyMap.put(CaptchaStrategyScope.COOKIE, new CookieCaptchaStrategy());
        captchaStrategyMap.put(CaptchaStrategyScope.HIDDEN, new HiddenFieldCaptchaStrategy());

    }

    /**
     * obtain  captcha strategy  by strategy scope
     * @param strategyScope - key of captcha strategy
     * @return  {@link CaptchaStrategy}
     */
    public CaptchaStrategy getStrategy(CaptchaStrategyScope strategyScope) {
        return captchaStrategyMap.get(strategyScope);
    }
}
