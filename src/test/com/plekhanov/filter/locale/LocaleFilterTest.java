package com.plekhanov.filter.locale;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static com.plekhanov.constant.filter.LocaleFilterConstants.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LocaleFilterTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain filterChain;
    @Mock
    private FilterConfig filterConfig;
    @Mock
    private HttpSession session;
    private LocaleFilter localeFilter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        localeFilter = new LocaleFilter();
        when(filterConfig.getInitParameter(SCOPE)).thenReturn("session");
        when(filterConfig.getInitParameter(TIMEOUT)).thenReturn("100");
        when(request.getCookies())
                .thenReturn(new Cookie[]{new Cookie(LOCALE, "ru")});
        when(request.getSession()).thenReturn(session);
        localeFilter.init(filterConfig);

    }
    @Test
    public void shouldSetLocaleWithoutUserLocales() throws IOException, ServletException {
       //given
        List<Locale> localeList = new ArrayList<>();
        localeList.add(Locale.ENGLISH);
        //when
        when(session.getAttribute(LOCALE)).thenReturn(null);
        when(request.getLocales()).thenReturn(Collections.enumeration(localeList));
        when(filterConfig.getInitParameter(LOCALE)).thenReturn("ru en ua");
        localeFilter.doFilter(request, response,filterChain);
        //then
        verify(filterChain).doFilter(any(), any());
        localeFilter.destroy();
    }
    @Test
    public void shouldSetLocaleByUserLocales() throws IOException, ServletException {
        String locale = "en";
        //when
        when(session.getAttribute(LOCALE)).thenReturn(locale);
        localeFilter.doFilter(request, response,filterChain);
        //then
        verify(filterChain).doFilter(any(), any());
        localeFilter.destroy();
    }
}