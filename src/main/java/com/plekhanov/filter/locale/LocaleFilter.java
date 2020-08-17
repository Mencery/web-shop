package com.plekhanov.filter.locale;

import com.plekhanov.filter.locale.requestwrapper.LocaleRequestWrapper;
import com.plekhanov.filter.locale.strategy.LocaleStorageStrategy;
import com.plekhanov.filter.locale.strategy.factory.LocaleStrategyFactory;
import com.plekhanov.filter.locale.strategy.scope.LocaleStrategyScope;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.plekhanov.constant.filter.LocaleFilterConstants.*;

public class LocaleFilter implements Filter {
    private FilterConfig config;
    private LocaleStorageStrategy localeStorageStrategy;
    private int timeout;
    /**
     * @param filterConfig - {@link FilterConfig}
     */
    @Override
    public void init(FilterConfig filterConfig) {
        config = filterConfig;
        LocaleStrategyFactory strategyFactory = new LocaleStrategyFactory();
        LocaleStrategyScope scope = LocaleStrategyScope
                .valueOf(config.getInitParameter(SCOPE).toUpperCase());
        localeStorageStrategy = strategyFactory.getStrategy(scope);
        timeout = Integer.parseInt(config.getInitParameter(TIMEOUT));
    }
    /**
     * set locale for site
     * @param servletRequest  - {@link ServletRequest }
     * @param servletResponse - {@link ServletResponse}
     * @param filterChain     - {@link FilterChain}
     * @throws IOException      - can throws {@link IOException}
     * @throws ServletException - can throws {@link ServletException}
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        LocaleRequestWrapper localeRequestWrapper = new LocaleRequestWrapper(req);
        setLocale(localeRequestWrapper, res);
        setSelectedLocale(localeRequestWrapper, res);

        Locale locale = localeStorageStrategy.getLocale(localeRequestWrapper);
        setWrappersLocale(localeRequestWrapper, locale);

        req = localeRequestWrapper;
        localeStorageStrategy.setTimeOut(req,timeout);
        res.setLocale(locale);
        filterChain.doFilter(req, res);
    }

    /**
     * destroy filter
     */
    @Override
    public void destroy() {

    }

    /**
     *
     * @return available locales from filter's parameter
     */
    private List<Locale> getAvailableLocales() {
        String locales = config.getInitParameter(LOCALE);
        List<Locale> localeList = new ArrayList<>();
        for (String l : locales.split(" ")) {
            localeList.add(Locale.forLanguageTag(l));
        }
        return localeList;
    }

    /**
     * set locale in storage
     * @param req - {@link LocaleRequestWrapper}
     * @param res - {@link HttpServletResponse}
     */
    private void setLocale(LocaleRequestWrapper req, HttpServletResponse res) {
        if (localeStorageStrategy.getLocale(req) == null) {
            Locale current = Locale.ENGLISH;
            List<Locale> locales = Collections.list(req.getLocales());
            List<Locale> availableLocales = getAvailableLocales();
            for (Locale l : locales) {
                if (availableLocales.contains(l)) {
                    current = l;
                    break;
                }
            }
            localeStorageStrategy.setLocale(req, res, current.toString());
        }
    }

    /**
     *set locale in storage if user select some language
     * @param req - {@link LocaleRequestWrapper}
     * @param res - {@link HttpServletResponse}
     */
    private void setSelectedLocale(LocaleRequestWrapper req, HttpServletResponse res) {
        String selectedLanguage = req.getParameter(LANGUAGE);
        if (selectedLanguage != null) {
            localeStorageStrategy.setLocale(req, res, selectedLanguage);
        }
    }

    /**
     *set locale in wrapper
     * @param localeRequestWrapper  - {@link LocaleRequestWrapper}
     * @param locale - string that contains language sign
     */
    private void setWrappersLocale(LocaleRequestWrapper localeRequestWrapper, Locale locale) {
        localeRequestWrapper.setLocale(locale);
        List<Locale> localeList = new ArrayList<>();
        localeList.add(locale);
        Enumeration<Locale> locales = Collections.enumeration(localeList);
        localeRequestWrapper.setLocales(locales);
    }
}
