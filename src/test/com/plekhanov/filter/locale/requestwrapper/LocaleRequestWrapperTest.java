package com.plekhanov.filter.locale.requestwrapper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.plekhanov.constant.filter.LocaleFilterConstants.LOCALE;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class LocaleRequestWrapperTest {

    @Mock
    private HttpServletRequest httpServletRequest;

    private LocaleRequestWrapper localizationWrapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(httpServletRequest.getCookies())
                .thenReturn(new Cookie[]{new Cookie(LOCALE, "ru")});
        localizationWrapper = new LocaleRequestWrapper(httpServletRequest);
    }


    @Test
    public void shouldReturnSuccessLocale() {
        //when
        localizationWrapper.setLocale(Locale.ENGLISH);
        //then
        assertNotNull(localizationWrapper.getLocale());
    }

    @Test
    public void shouldReturnLocales() {
        //given
        List<Locale> localeList = new ArrayList<>();
        localeList.add(Locale.ENGLISH);
        //when
        localizationWrapper.setLocales(Collections.enumeration(localeList));
        //then
        assertNotNull(localizationWrapper.getLocales());
    }
    @Test
    public void shouldReturnCookie(){
        //when
        localizationWrapper.setCookies(new Cookie(LOCALE,"ru"));
        localizationWrapper.setCookies(new Cookie(LOCALE,"ua"));
        //then
        assertNotNull(localizationWrapper.getCookies());
    }
}