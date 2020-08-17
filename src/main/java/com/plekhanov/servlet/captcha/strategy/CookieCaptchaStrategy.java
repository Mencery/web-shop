package com.plekhanov.servlet.captcha.strategy;

import com.plekhanov.servlet.captcha.Captcha;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.plekhanov.constant.servlet.captcha.CaptchaConstants.CAPTCHA_NUMBER;
import static com.plekhanov.constant.servlet.register.RegisterServletConstants.MAX_AGE_COOKIE;

/**
* class that save {@link Captcha} number in {@link Cookie}
*/
public class CookieCaptchaStrategy extends CaptchaStrategy {
    /**
     * save {@link Captcha} id in cookie
     * @param req - {@link HttpServletRequest}
     * @param resp - {@link HttpServletResponse}
     */
    @Override
    public void saveCaptcha(HttpServletRequest req, HttpServletResponse resp) {
        Cookie cookie = new Cookie(CAPTCHA_NUMBER, captcha.getId().toString());
        cookie.setMaxAge(MAX_AGE_COOKIE);
        resp.addCookie(cookie);
        super.saveCaptcha(req, resp);
    }

    /**
     * obtain {@link Captcha} number from cookies
     * @param req - {@link HttpServletRequest}
     * @return captcha number
     */
    @Override
    public String getNumber(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (CAPTCHA_NUMBER.equals(cookie.getName())) {
                return getValueFromContext(req, cookie.getValue());
            }
        }
        throw new NullPointerException();
    }
}
