package com.plekhanov.servlet.captcha.strategy;

import com.plekhanov.constant.servlet.captcha.CaptchaConstants;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpSession;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class SessionCaptchaStrategyTest extends CaptchaStrategyTest {
    @Mock
    HttpSession session;

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        setUp();
        strategy = new SessionCaptchaStrategy();
        strategy.generateCaptcha();
        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void shouldSaveCaptcha_whenUsingSessionCaptchaStrategy() {
        strategy.saveCaptcha(request, response);
        verify(session).setAttribute(eq(CaptchaConstants.CAPTCHA_NUMBER), anyString());
    }
}
