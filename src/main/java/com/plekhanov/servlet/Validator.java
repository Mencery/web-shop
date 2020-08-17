package com.plekhanov.servlet;

import com.plekhanov.entity.product.Product;
import com.plekhanov.servlet.captcha.Captcha;
import com.plekhanov.servlet.captcha.strategy.CaptchaStrategy;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

import static com.plekhanov.constant.servlet.captcha.CaptchaConstants.CAPTCHA;
import static com.plekhanov.constant.servlet.captcha.CaptchaConstants.USER_INPUT_CAPTCHA;
import static com.plekhanov.constant.listener.ListenerConstants.CAPTCHA_STRATEGY;

import static com.plekhanov.constant.servlet.register.RegisterServletConstants.*;
import static com.plekhanov.constant.servlet.register.RegisterServletConstants.ERROR_MASSAGE_WRONG_INPUT;

public class Validator {
    /**
     * validate {@link Captcha} number
     *
     * @param req    - {@link HttpServletRequest}
     * @param errors - map of errors
     */
    public static void validateCaptcha(HttpServletRequest req, Map<String, String> errors) {
        CaptchaStrategy captchaStrategy = (CaptchaStrategy) req.getServletContext().getAttribute(CAPTCHA_STRATEGY);
        String captchaNumber = captchaStrategy.getNumber(req);
        String inputCaptcha = req.getParameter(USER_INPUT_CAPTCHA);
        if (captchaNumber == null) {
            errors.put(CAPTCHA, ERROR_MASSAGE_TIME_OUT);
            return;
        }
        if (!Objects.equals(captchaNumber, inputCaptcha)) {
            errors.put(CAPTCHA, ERROR_MASSAGE_WRONG_INPUT);
        }
    }

    /**
     * @param mailings - input checkbox
     * @return clicked mailings
     */
    public static boolean isMailings(String[] mailings) {
        return mailings != null;
    }

    /**
     * @param errors    - map of errors
     * @param paramName - name of parameter
     * @param parameter - value
     * @param regex     - parameter regex
     */
    public static void validateField(Map<String, String> errors, String paramName, String parameter, String regex) {
        checkByMatcher(errors, paramName, parameter, regex);
        checkNullParameter(errors, paramName, parameter);
    }

    /**
     * checks by matcher
     *
     * @param errors    - map of errors
     * @param paramName - name of parameter
     * @param parameter - value
     * @param regex     - parameter regex
     */
    private static void checkByMatcher(Map<String, String> errors, String paramName, String parameter, String regex) {
        if (!parameter.matches(regex)) {
            errors.put(paramName, ERROR_MASSAGE_INCORRECT_INPUT + parameter);
        }
    }

    /**
     * check on null
     *
     * @param errors    - map of errors
     * @param paramName - name of parameter
     * @param parameter - value
     */
    private static void checkNullParameter(Map<String, String> errors, String paramName, String parameter) {
        if (parameter == null) {
            errors.put(paramName, ERROR_MASSAGE_FIELD_IS_EMPTY);
        }
    }

    /**
     * @param password - password from database
     * @param input    - password that input user
     * @return true if equals
     */
    public static boolean comparePasswords(String password, String input) {
        return Objects.equals(password, input);
    }

    /**
     *
     * @param input - client number
     * @param productMin - {@link Product} min
     * @param productMax - {@link Product} min
     * @return true if  input correct
     */
    public static boolean checkInputMinMax(String input, int productMin, int productMax) {
        if (input != null) {
            try {
                int minmax = Integer.parseInt(input);
                return productMin <= minmax && productMax >= minmax;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return false;
    }

    /**
     *
     * @param min -
     * @param max -
     * @return true if min less then max
     */
    public static boolean compareMinMax(int min, int max) {
        return max >= min;
    }

    /**
     *
     * @param number - checks
     * @return true if  number correct
     */
    public static boolean checkRawAndColNumbers(String number) {
        if (number != null) {
            try {
                int numberOnPage = Integer.parseInt(number);
               return numberOnPage > 0;
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }
}
