package com.plekhanov.filter.locale.strategy;

import com.plekhanov.filter.locale.requestwrapper.LocaleRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * strategy of storage locale
 */
public interface LocaleStorageStrategy {
    /**
     * @param req - {@link LocaleRequestWrapper}
     * @return storaged locale
     */
    Locale getLocale(LocaleRequestWrapper req);

    /**
     *
     * @param req - {@link LocaleRequestWrapper}
     * @param res - {@link HttpServletResponse}
     * @param locale - string that contains language sign
     */
    void setLocale(LocaleRequestWrapper req, HttpServletResponse res, String locale);

    /**
     *
     * @param req - {@link LocaleRequestWrapper}
     * @param time - set cookie time of live
     */
    void setTimeOut(HttpServletRequest req,int time);
}
