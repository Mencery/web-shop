package com.plekhanov.filter.compression;

import com.plekhanov.constant.filter.CompressionFilterConstants;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class CompressionFilterTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain filterChain;
    @Mock
    private FilterConfig filterConfig;
    private CompressionFilter compressionFilter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        compressionFilter = new CompressionFilter();
    }

    @Test
    public void shouldSetUpInitParametersAndDestroyNoException() {
        //when
        compressionFilter.init(filterConfig);
        compressionFilter.destroy();
    }

    @Test
    public void shouldCompressPage() throws IOException, ServletException {
      when(request.getHeader(CompressionFilterConstants.ACCEPT_ENCODING)).thenReturn("gzip");
      compressionFilter.doFilter(request, response,filterChain);
      verify(filterChain).doFilter(any(), any());
    }
    @Test
    public void shouldDontCompressPage() throws IOException, ServletException {
        //when
        when(request.getHeader(CompressionFilterConstants.ACCEPT_ENCODING)).thenReturn("xml");
        compressionFilter.doFilter(request, response,filterChain);
        //then
        verify(filterChain).doFilter(any(), any());
    }
}