package com.plekhanov.filter;

import com.plekhanov.servlet.login.LoginServlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static com.plekhanov.constant.listener.ListenerConstants.CURRENT_USER;
import static com.plekhanov.constant.Paths.*;

/**
 * checks logged in user
 */
public class LoginFilter implements Filter {
    /**
     * @param filterConfig - {@link FilterConfig}
     */
    @Override
    public void init(FilterConfig filterConfig) {
    }

    /**
     * sends to {@link LoginServlet} if user haven't logged in
     *
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
        if (req.getSession().getAttribute(CURRENT_USER) == null) {
            res.sendRedirect(LOGIN_SERVLET);
        } else {
            filterChain.doFilter(req, res);
        }
    }

    /**
     * destroy filter
     */
    @Override
    public void destroy() {
    }
}
