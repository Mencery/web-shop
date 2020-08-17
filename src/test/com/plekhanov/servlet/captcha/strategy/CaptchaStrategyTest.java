package com.plekhanov.servlet.captcha.strategy;

import com.plekhanov.constant.listener.ListenerConstants;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static org.mockito.Mockito.when;

public class CaptchaStrategyTest {
    @Mock
    protected HttpServletRequest request;
    @Mock
    protected HttpServletResponse response;
    @Mock
    protected ServletContext servletContext;
    @Mock
    protected ServletOutputStream outputStream;
    protected ConcurrentHashMap<AtomicLong, String> map;
    protected CaptchaStrategy strategy;

    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        map = new ConcurrentHashMap<>();
        String timeout = "300";
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute(ListenerConstants.CONTEXT_CAPTCHA_MAP)).thenReturn(map);
        when(servletContext.getInitParameter(ListenerConstants.CAPTCHA_TIME_OUT)).thenReturn(timeout);
        when(response.getOutputStream()).thenReturn(outputStream);

    }
}
