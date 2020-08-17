package com.plekhanov.constant.servlet.captcha;

public class CaptchaConstants {
    public static final String ENABLE_CHARS = "0123456789";
    public static final String ERROR_MASSAGE = "Unable to build image";
    public static final String PNG = "png";
    public static final String CAPTCHA = "captcha";
    public static final String HIDDEN_CAPTCHA_FIELD = "captcha-number";
    public static final String USER_INPUT_CAPTCHA = "user-input-captcha";
    public static final String IMG_CAPTCHA = "<div class=\"form-group\">" +
            "<img src=\"/shop/captcha.do\" alt=\"Captcha not found\" />" +
            "</div>";
    public static final String INPUT_CAPTCHA = "<div class=\"form-group\">" +
            "<div class=\"input-group input-group-sm\">\n" +
            "<input type=\"text\"  class=\"form-control\" name=\"" + USER_INPUT_CAPTCHA + "\" data-pattern=\".+\">" +
            "</div>" +
            "</div>";
    public static final String CAPTCHA_NUMBER = "captcha-number";
}
