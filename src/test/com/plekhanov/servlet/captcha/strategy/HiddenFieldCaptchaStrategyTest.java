package com.plekhanov.servlet.captcha.strategy;

import com.plekhanov.constant.servlet.captcha.CaptchaConstants;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;

public class HiddenFieldCaptchaStrategyTest extends CaptchaStrategyTest {

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        setUp();
        strategy = new HiddenFieldCaptchaStrategy();
        strategy.generateCaptcha();
    }

    @Test
    public void shouldSaveCaptcha_whenUsingHiddenFieldCaptchaStrategy() {
        strategy.saveCaptcha(request, response);
        verify(request).setAttribute(eq(CaptchaConstants.HIDDEN_CAPTCHA_FIELD), anyString());
    }

    @Test
    public void shouldGetCaptcha_whenUsingHiddenFieldCaptchaStrategy() {
        strategy.getNumber(request);
        verify(request).getParameter(CaptchaConstants.CAPTCHA_NUMBER);
    }
}
