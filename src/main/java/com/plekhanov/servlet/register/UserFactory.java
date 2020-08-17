package com.plekhanov.servlet.register;

import com.plekhanov.entity.user.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.plekhanov.constant.user.UserConstants.*;

/**
 * factory that fills {@link User}
 */
public class UserFactory {
    private Map<String, Consumer<String>> userSetterMap;
    private Map<String, Supplier<String>> userGetterMap;
    private Map<String, String> fieldRegex;
    public User user;

    /**
     * initialization userSetterMap, userGetterMap and fieldRegex
     *
     * @param user - user that filling
     */
    public UserFactory(User user) {
        this.user = user;
        userSetterMap = new HashMap<>();
        userGetterMap = new HashMap<>();
        fieldRegex = new HashMap<>();

        userSetterMap.put(NAME, user::setName);
        userSetterMap.put(SURNAME, user::setSurname);
        userSetterMap.put(EMAIL, user::setEmail);
        userSetterMap.put(PASSWORD, user::setPassword);

        userGetterMap.put(NAME, user::getName);
        userGetterMap.put(SURNAME, user::getSurname);
        userGetterMap.put(EMAIL, user::getEmail);
        userGetterMap.put(PASSWORD, user::getPassword);

        fieldRegex.put(NAME, NAME_REGEX);
        fieldRegex.put(SURNAME, SURNAME_REGEX);
        fieldRegex.put(EMAIL, EMAIL_REGEX);
        fieldRegex.put(PASSWORD, PASSWORD_REGEX);
    }

    /**
     * set parameter
     *
     * @param paramName - name of parameter
     * @param parameter - value
     */
    public void setParameter(String paramName, String parameter) {
        userSetterMap.get(paramName).accept(parameter);
    }

    /**
     * @return all setter keys
     */
    public Set<String> getKeySet() {
        return userSetterMap.keySet();
    }

    /**
     * @param paramName - name of parameter
     * @return parameter by name of parameter
     */
    public String getParameter(String paramName) {
        return userGetterMap.get(paramName).get();
    }

    /**
     * set mailings
     *
     * @param isMailing - mailings
     */
    public void setMailings(boolean isMailing) {
        user.setMailings(isMailing);
    }

    /**
     * @param paramName - name of parameter
     * @return regex by name of parameter
     */
    public String getRegex(String paramName) {
        return fieldRegex.get(paramName);
    }
}
