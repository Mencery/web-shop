package com.plekhanov.filter.locale.requestwrapper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * wrapper {@link HttpServletRequest} for locale
 */
public class LocaleRequestWrapper extends HttpServletRequestWrapper {
    private Locale locale;
    private Enumeration<Locale> locales;
    private Set<Cookie> cookies;

    /**
     * calls super constructor and initializes {@link Set<Cookie>}
     * @param httpServletRequest - {@link HttpServletRequest}
     */
    public LocaleRequestWrapper(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
        cookies = new HashSet<>();
        if(httpServletRequest.getCookies() != null) {
            cookies.addAll(Arrays.asList(httpServletRequest.getCookies()));
        }
        locales = httpServletRequest.getLocales();
    }

    @Override
    public Cookie[] getCookies() {
        return cookies.toArray(new Cookie[0]);
    }

    /**
     * add cookie
     * @param cookie - cookies that need set
     */
    public void setCookies(Cookie cookie) {
        Optional<Cookie> optional = cookies.stream().filter(c -> c.getName().equals(cookie.getName())).findAny();
        optional.ifPresent(value -> cookies.remove(value));
        this.cookies.add(cookie);
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public Enumeration<Locale> getLocales() {
        return locales;
    }

    public void setLocales(Enumeration<Locale> locales) {
        this.locales = locales;
    }
}
