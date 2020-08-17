package com.plekhanov.filter.locale.strategy;

import com.plekhanov.filter.locale.requestwrapper.LocaleRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

import static com.plekhanov.constant.filter.LocaleFilterConstants.LOCALE;

/**
 * strategy of storage locale in session
 */
public class SessionStorageStrategy implements LocaleStorageStrategy {
    /**
     * @param req - {@link LocaleRequestWrapper}
     * @return storaged locale
     */
    @Override
    public Locale getLocale(LocaleRequestWrapper req) {
        String locale = (String) req.getSession().getAttribute(LOCALE);
        return locale == null ? null : Locale.forLanguageTag(locale);
    }

    /**
     * @param req    - {@link LocaleRequestWrapper}
     * @param res    - {@link HttpServletResponse}
     * @param locale - string that contains language sign
     */
    @Override
    public void setLocale(LocaleRequestWrapper req, HttpServletResponse res, String locale) {
        req.getSession().setAttribute(LOCALE, locale);
    }

    /**
     * not supported for session
     */
    @Override
    public void setTimeOut(HttpServletRequest req, int time) {

    }
}
