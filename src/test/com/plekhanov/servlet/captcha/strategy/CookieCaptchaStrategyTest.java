package com.plekhanov.servlet.captcha.strategy;

import com.plekhanov.constant.servlet.captcha.CaptchaConstants;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.Cookie;

import static org.mockito.Mockito.*;

public class CookieCaptchaStrategyTest extends CaptchaStrategyTest {
    private String number = "1";
    private Cookie[] cookies;

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        setUp();
        strategy = new CookieCaptchaStrategy();
        cookies = new Cookie[1];
        strategy.generateCaptcha();
    }

    @Test
    public void shouldSaveCaptcha_whenUsingCookieCaptchaStrategy() {
        strategy.saveCaptcha(request, response);
        verify(response).addCookie(any(Cookie.class));
    }

    @Test
    public void shouldGetCaptcha_whenRequestReturnCookieWithCorrectNameAndValue() {
        cookies[0] = new Cookie(CaptchaConstants.CAPTCHA_NUMBER, number);
        when(request.getCookies()).thenReturn(cookies);
        strategy.getNumber(request);
    }

    @Test(expected = NullPointerException.class)
    public void shouldFailGetCaptcha_whenRequestReturnCookieWithIncorrectNameAndValue() {
        cookies[0] = new Cookie(number, number);
        when(request.getCookies()).thenReturn(cookies);
        strategy.getNumber(request);
    }
}
