package com.plekhanov.filter.compression;


import com.plekhanov.constant.filter.CompressionFilterConstants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * compresses text in gzip
 */
public class CompressionFilter implements Filter {
    /**
     * @param filterConfig - {@link FilterConfig}
     */
    @Override
    public void init(FilterConfig filterConfig) {

    }
    /**
     * compresses text in gzip
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
        if (acceptsGZipEncoding(req)) {
            res.addHeader(CompressionFilterConstants.CONTENT_ENCODING, CompressionFilterConstants.GZIP);
            GZipServletResponseWrapper gzipResponse =
                    new GZipServletResponseWrapper(res);
            filterChain.doFilter(req, gzipResponse);
            gzipResponse.close();
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

    private boolean acceptsGZipEncoding(HttpServletRequest httpRequest) {
        String acceptEncoding =
                httpRequest.getHeader(CompressionFilterConstants.ACCEPT_ENCODING);

        return acceptEncoding != null &&
                acceptEncoding.contains(CompressionFilterConstants.GZIP);
    }
}
