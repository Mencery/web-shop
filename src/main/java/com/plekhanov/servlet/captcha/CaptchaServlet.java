package com.plekhanov.servlet.captcha;

import com.plekhanov.service.CaptchaService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import static com.plekhanov.constant.Paths.CAPTCHA_SERVLET;
import static com.plekhanov.constant.Paths.SLASH;

/**
 * servlet that handles {@link Captcha}
 */
@WebServlet(SLASH + CAPTCHA_SERVLET)
public class CaptchaServlet extends HttpServlet {
    /**
     * invoke captcha service
     *
     * @param req  - {@link HttpServletRequest}
     * @param resp - {@link HttpServletResponse}
     */
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        CaptchaService captchaService = new CaptchaService();
        captchaService.createCaptcha(req, resp);
    }
}
