package com.plekhanov.servlet.captcha.strategy;

import com.plekhanov.servlet.captcha.Captcha;
import com.plekhanov.servlet.captcha.CaptchaGenerator;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static com.plekhanov.constant.listener.ListenerConstants.*;
import static com.plekhanov.constant.servlet.captcha.CaptchaConstants.PNG;

/**
 * class parent for captcha strategy classes
 */
public abstract class CaptchaStrategy {
    protected Captcha captcha;

    /**
     * empty constructor
     */
    public CaptchaStrategy() {
    }

    /**
     * generate captcha invoke captcha generator
     */
    public void generateCaptcha() {
        captcha = new Captcha();
        CaptchaGenerator captchaGenerator = new CaptchaGenerator();
        captchaGenerator.generate(captcha);
    }

    /**
     * save captcha number, set image on page, run timeout
     *
     * @param req  - {@link HttpServletRequest}
     * @param resp - {@link HttpServletResponse}
     */
    public void saveCaptcha(HttpServletRequest req, HttpServletResponse resp) {
        setNumber(req);
        setImage(resp);
        setTimeOut(req);
    }

    /**
     * @param req - {@link HttpServletRequest}
     * @return captcha number
     */
    public abstract String getNumber(HttpServletRequest req);

    /**
     * @param req - {@link HttpServletRequest}
     * @param id  - captcha id
     * @return captcha number from context map
     */
    @SuppressWarnings("unchecked")
    protected String getValueFromContext(HttpServletRequest req, String id) {
        ServletContext context = req.getServletContext();
        ConcurrentMap<AtomicLong, String> map = (ConcurrentMap<AtomicLong, String>) context.getAttribute(CONTEXT_CAPTCHA_MAP);
        Optional<AtomicLong> key = map.keySet().stream().filter(k -> id.equals(k.toString())).findFirst();
        return key.map(map::get).orElse(null);
    }

    /**
     * run timeout. after this time captcha deletes
     *
     * @param req - {@link HttpServletRequest}
     */
    private void setTimeOut(HttpServletRequest req) {
        ConcurrentMap<AtomicLong, String> map = getMapFromContext(req);
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.schedule(() -> new Thread(() -> map.remove(captcha.getId())).start(), getTimeOutFromContext(req), TimeUnit.SECONDS);
    }

    /**
     * set image on page
     *
     * @param resp - {@link HttpServletRequest}
     */
    private void setImage(HttpServletResponse resp) {
        try {
            ImageIO.write(captcha.getImage(), PNG, resp.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * set captcha number into map
     *
     * @param req - {@link HttpServletRequest}
     */
    private void setNumber(HttpServletRequest req) {
        getMapFromContext(req).put(captcha.getId(), captcha.getNumber());
    }

    /**
     * @param req - {@link HttpServletRequest}
     * @return captcha map from  context
     */
    @SuppressWarnings("unchecked")
    private ConcurrentMap<AtomicLong, String> getMapFromContext(HttpServletRequest req) {
        ServletContext context = req.getServletContext();
        return (ConcurrentMap<AtomicLong, String>) context.getAttribute(CONTEXT_CAPTCHA_MAP);
    }

    /**
     * @param req - {@link HttpServletRequest}
     * @return timeout from context
     */
    private int getTimeOutFromContext(HttpServletRequest req) {
        return Integer.parseInt(req.getServletContext().getInitParameter(CAPTCHA_TIME_OUT));
    }
}
