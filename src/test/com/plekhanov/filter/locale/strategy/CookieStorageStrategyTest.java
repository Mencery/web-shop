package com.plekhanov.filter.locale.strategy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.Cookie;
import java.util.Locale;

import static com.plekhanov.constant.filter.LocaleFilterConstants.LOCALE;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CookieStorageStrategyTest extends LocaleStorageStrategyTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
        MockitoAnnotations.initMocks(this);
        localeStorageStrategy = new CookieStorageStrategy();
    }

    @Test
    public void shouldReturnLocale() {
        //given
        String locale = "en";
        //when
        when(localeRequestWrapper.getCookies())
                .thenReturn(new Cookie[]{new Cookie(LOCALE, locale)});
        //then
        Assert.assertEquals(Locale.ENGLISH,
                localeStorageStrategy.getLocale(localeRequestWrapper));
    }

    @Test
    public void shouldReturnNull() {
        //given
        String locale = null;
        //when
        when(localeRequestWrapper.getCookies())
                .thenReturn(null);
        //then
        Assert.assertNull(localeStorageStrategy.getLocale(localeRequestWrapper));
    }

    @Test
    public void shouldSetLocale() {
        //given
        String locale = "en";
        //when
        localeStorageStrategy.setLocale(localeRequestWrapper, response, locale);
        //then
        verify(localeRequestWrapper).setCookies(any());
    }

    @Test
    public void shouldSetCookieTimeOut() {
        //given
        String locale = "en";
        //when
        when(localeRequestWrapper.getCookies())
                .thenReturn(new Cookie[]{new Cookie(LOCALE, locale)});
        localeStorageStrategy.setTimeOut(localeRequestWrapper, 1);
    }
}