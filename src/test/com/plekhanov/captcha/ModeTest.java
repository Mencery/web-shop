package com.plekhanov.captcha;

import com.plekhanov.servlet.captcha.CaptchaServlet;
import com.plekhanov.servlet.captcha.strategy.CaptchaStrategy;
import com.plekhanov.servlet.captcha.strategy.CookieCaptchaStrategy;
import com.plekhanov.servlet.captcha.strategy.HiddenFieldCaptchaStrategy;
import com.plekhanov.servlet.captcha.strategy.SessionCaptchaStrategy;
import com.plekhanov.constant.listener.ListenerConstants;
import com.plekhanov.constant.servlet.captcha.CaptchaConstants;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static org.mockito.Mockito.*;

public class ModeTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private CaptchaServlet captchaServlet;
    private ServletContext servletContext;
    private ServletOutputStream outputStream;
    private CaptchaStrategy captchaStrategy;
    private ConcurrentHashMap<AtomicLong, String> map;

    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        captchaServlet = new CaptchaServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        servletContext = mock(ServletContext.class);
        outputStream = mock(ServletOutputStream.class);
        map = mock(ConcurrentHashMap.class);
        when(request.getServletContext()).thenReturn(servletContext);

    }

    @Test
    public void shouldHandleCaptcha_whenUsingSessionCaptchaMode() throws IOException {
        captchaStrategy = new SessionCaptchaStrategy();
        HttpSession session = mock(HttpSession.class);
        fillAndStartWithStrategy(captchaStrategy);
        when(request.getSession()).thenReturn(session);

        captchaServlet.doGet(request, response);
        verify(request).getSession();
        verify(session).setAttribute(eq(CaptchaConstants.CAPTCHA_NUMBER), anyString());
    }

    @Test
    public void shouldHandleCaptcha_whenUsingCookieCaptchaMode() throws IOException {
        captchaStrategy = new CookieCaptchaStrategy();
        fillAndStartWithStrategy(captchaStrategy);
        captchaServlet.doGet(request, response);
        verify(response).addCookie(any(Cookie.class));
    }

    @Test
    public void shouldHandleCaptcha_whenUsingHiddenFieldCaptchaMode() throws IOException {
        captchaStrategy = new HiddenFieldCaptchaStrategy();
        fillAndStartWithStrategy(captchaStrategy);
        captchaServlet.doGet(request, response);
        verify(request).setAttribute(eq(CaptchaConstants.HIDDEN_CAPTCHA_FIELD), any(AtomicLong.class));
    }

    private void fillAndStartWithStrategy(CaptchaStrategy captchaStrategy) throws IOException {
        when(servletContext.getAttribute(ListenerConstants.CAPTCHA_STRATEGY)).thenReturn(captchaStrategy);
        when(servletContext.getAttribute(ListenerConstants.CONTEXT_CAPTCHA_MAP)).thenReturn(map);
        when(response.getOutputStream()).thenReturn(outputStream);
        String timeOut = "10";
        when(request.getServletContext().getInitParameter(ListenerConstants.CAPTCHA_TIME_OUT)).thenReturn(timeOut);
    }
}
