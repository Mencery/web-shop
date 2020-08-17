package com.plekhanov.filter;

import com.plekhanov.repository.ProductRepository;
import com.plekhanov.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.plekhanov.constant.listener.ListenerConstants.CONTEXT_PRODUCT_SERVICE;
import static com.plekhanov.constant.Paths.INDEX_PAGE;

/**
 * class checks products in {@link ProductRepository}
 */
public class ProductFilter implements Filter {
    /**
     * @param filterConfig - {@link FilterConfig}
     */
    @Override
    public void init(FilterConfig filterConfig) {
    }

    /**
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
        ProductService productService = (ProductService) req.getServletContext().getAttribute(CONTEXT_PRODUCT_SERVICE);
        if (productService.getCurrentProducts() == null) {
            res.sendRedirect(INDEX_PAGE);
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
