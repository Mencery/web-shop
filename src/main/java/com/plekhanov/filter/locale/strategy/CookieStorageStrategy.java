package com.plekhanov.filter.locale.strategy;

import com.plekhanov.filter.locale.requestwrapper.LocaleRequestWrapper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

import static com.plekhanov.constant.filter.LocaleFilterConstants.LOCALE;

/**
 * strategy of storage locale in cookie
 */
public class CookieStorageStrategy implements LocaleStorageStrategy {
    /**
     * @param req - {@link LocaleRequestWrapper}
     * @return storaged locale
     */
    @Override
    public Locale getLocale(LocaleRequestWrapper req) {
        if (req.getCookies() != null) {
            for (Cookie c : req.getCookies()) {
                if (LOCALE.equals(c.getName())) {
                    return Locale.forLanguageTag(c.getValue());
                }
            }
        }
        return null;
    }
    /**
     *
     * @param req - {@link LocaleRequestWrapper}
     * @param res - {@link HttpServletResponse}
     * @param locale - string that contains language sign
     */
    @Override
    public void setLocale(LocaleRequestWrapper req, HttpServletResponse res, String locale) {
        Cookie cookie = new Cookie(LOCALE, locale);
        req.setCookies(cookie);
        res.addCookie(cookie);
    }

    /**
     *
     * @param req - {@link LocaleRequestWrapper}
     * @param time - set cookie time of live
     */
    @Override
    public void setTimeOut(HttpServletRequest req, int time) {
        if (req.getCookies() != null) {
            for (Cookie c : req.getCookies()) {
                if (LOCALE.equals(c.getName())) {
                    c.setMaxAge(time);
                }
            }
        }
    }
}
