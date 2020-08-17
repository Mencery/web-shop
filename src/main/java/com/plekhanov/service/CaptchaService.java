package com.plekhanov.service;

import com.plekhanov.servlet.captcha.CaptchaServlet;
import com.plekhanov.servlet.captcha.strategy.CaptchaStrategy;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.plekhanov.constant.listener.ListenerConstants.CAPTCHA_STRATEGY;

/**
 * service for {@link CaptchaServlet}
 */
public class CaptchaService {
    /**
     * create captcha image
     *
     * @param req  - {@link HttpServletRequest}
     * @param resp - {@link HttpServletResponse}
     */
    public void createCaptcha(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext context = req.getServletContext();
        CaptchaStrategy captchaStrategy = (CaptchaStrategy) context.getAttribute(CAPTCHA_STRATEGY);
        captchaStrategy.generateCaptcha();
        captchaStrategy.saveCaptcha(req, resp);
    }
}
