package com.plekhanov.filter.security;

import com.plekhanov.entity.user.User;
import com.plekhanov.service.SecurityService;
import com.plekhanov.constant.Paths;
import com.plekhanov.constant.listener.ListenerConstants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * compares user and page permission
 */
public class SecurityFilter implements Filter {
    private SecurityService service;

    /**
     * initialize {@link SecurityService}
     * @param filterConfig - {@link FilterConfig}
     */
    @Override
    public void init(FilterConfig filterConfig) {
        service = new SecurityService();
    }
    /**
     * compares user and page permission
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
        String url = req.getRequestURL().toString();
        if (service.isNoPermissionPage(url)) {
            filterChain.doFilter(req, res);
            return;
        }
        User user = (User) req.getSession().getAttribute(ListenerConstants.CURRENT_USER);
        if (user == null) {
            req.getSession().setAttribute(ListenerConstants.REQUESTED_PAGE,
                    url);
            res.sendRedirect(Paths.LOGIN_SERVLET);
            return;
        }
        if (service.isAccessForUser(url, user)) {
            filterChain.doFilter(req, res);
            return;
        }
        res.sendRedirect(Paths.ERROR_403_PAGE);
    }
    /**
     * destroy filter
     */
    @Override
    public void destroy() {

    }
}
