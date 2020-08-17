package com.plekhanov.filter.locale.strategy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpSession;

import java.util.Locale;

import static com.plekhanov.constant.filter.LocaleFilterConstants.LOCALE;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SessionStorageStrategyTest extends LocaleStorageStrategyTest {
    @Mock
    private HttpSession session;
    @Override
    @Before
    public void setUp() {
        super.setUp();
        MockitoAnnotations.initMocks(this);
        localeStorageStrategy = new SessionStorageStrategy();
    }

    @Test
    public void shouldReturnLocale() {
        //given
        String locale = "en";
        //when
        when(localeRequestWrapper.getSession()).thenReturn(session);
        when(session.getAttribute(LOCALE)).thenReturn(locale);
        //then
        Assert.assertEquals(Locale.ENGLISH, localeStorageStrategy.getLocale(localeRequestWrapper));
    }

    @Test
    public void shouldReturnNull() {
        //given
        String locale = null;
        //when
        when(localeRequestWrapper.getSession()).thenReturn(session);
        when(session.getAttribute(LOCALE)).thenReturn(locale);
        //then
        Assert.assertNull(localeStorageStrategy.getLocale(localeRequestWrapper));
    }

    @Test
    public void shouldSetLocale() {
        //given
        String locale = "en";
        //when
        when(localeRequestWrapper.getSession()).thenReturn(session);
        localeStorageStrategy.setLocale(localeRequestWrapper, response, locale);
        //then
        verify(session).setAttribute(any(), anyString());
    }

    @Test
    public void shouldDoNothing() {
        localeStorageStrategy.setTimeOut(localeRequestWrapper,1);
    }
}