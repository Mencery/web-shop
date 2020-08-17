package com.plekhanov.servlet.captcha.strategy;

import com.plekhanov.servlet.captcha.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.plekhanov.constant.servlet.captcha.CaptchaConstants.CAPTCHA_NUMBER;
import static com.plekhanov.constant.servlet.captcha.CaptchaConstants.HIDDEN_CAPTCHA_FIELD;

/**
* class that save {@link Captcha} number in hidden field on page
*/
public class HiddenFieldCaptchaStrategy extends CaptchaStrategy {
    /**
     * save {@link Captcha} id in hidden field
     *
     * @param req  - {@link HttpServletRequest}
     * @param resp - {@link HttpServletResponse}
     */
    @Override
    public void saveCaptcha(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute(HIDDEN_CAPTCHA_FIELD, captcha.getId());
        super.saveCaptcha(req, resp);
    }

    /**
     * obtain captcha number from hidden field
     *
     * @param req - HttpServletRequest
     * @return captcha number
     */
    @Override
    public String getNumber(HttpServletRequest req) {
        return getValueFromContext(req, req.getParameter(CAPTCHA_NUMBER));
    }
}
