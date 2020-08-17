package com.plekhanov.filter;

import com.plekhanov.constant.filter.CacheFilterConstants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * turns off cache on page
 */
@WebFilter("/*")
public class CacheFilter implements Filter {
    /**
     * @param filterConfig - {@link FilterConfig}
     */
    @Override
    public void init(FilterConfig filterConfig)  {

    }

    /**
     * turns off cache on page
     * @param servletRequest  - {@link ServletRequest }
     * @param servletResponse - {@link ServletResponse}
     * @param filterChain     - {@link FilterChain}
     * @throws IOException      - can throws {@link IOException}
     * @throws ServletException - can throws {@link ServletException}
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        res.setHeader(CacheFilterConstants.CACHE_CONTROL, CacheFilterConstants.NO_CACHE);
        filterChain.doFilter(servletRequest, res);
    }

    /**
     * destroy filter
     */
    @Override
    public void destroy() {

    }
}
