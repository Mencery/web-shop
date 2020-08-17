package com.plekhanov.tag.captchatag;

import com.plekhanov.constant.servlet.captcha.CaptchaConstants;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import java.io.IOException;

/**
 *handles captcha tag
 */
public class CaptchaTag extends SimpleTagSupport {

    /**
     * print captcha on page
     *
     * @throws IOException - jspWriter throw
     */
    public void doTag() throws IOException {
        JspWriter jspWriter = getJspContext().getOut();
        jspWriter.println(CaptchaConstants.IMG_CAPTCHA + CaptchaConstants.INPUT_CAPTCHA);
    }
}
