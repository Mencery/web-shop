package com.plekhanov.servlet.captcha.strategy;

import com.plekhanov.servlet.captcha.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.plekhanov.constant.servlet.captcha.CaptchaConstants.CAPTCHA_NUMBER;

/**
* class that save {@link Captcha} number in @link HttpSession
*/
public class SessionCaptchaStrategy extends CaptchaStrategy {
    /**
     * save captcha id in session
     *
     * @param req  - {@link HttpServletRequest}
     * @param resp - {@link HttpServletResponse}
     */
    @Override
    public void saveCaptcha(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute(CAPTCHA_NUMBER, captcha.getId().toString());
        super.saveCaptcha(req, resp);
    }

    /**
     * obtain captcha number from session
     *
     * @param req - {@link HttpServletRequest}
     * @return captcha number
     */
    @Override
    public String getNumber(HttpServletRequest req) {
        return getValueFromContext(req, req.getSession().getAttribute(CAPTCHA_NUMBER).toString());
    }
}
