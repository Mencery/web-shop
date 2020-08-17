package com.plekhanov.constant.servlet.login;

public class LoginServletConstants {
    public static final String ERROR_MASSAGE_UNREGISTERED_USER = "unregistered user";
    public static final String ERROR_MASSAGE_INCORRECT_USER = "incorrect password";
    public static final String INDEX_REGISTER = "index.register";
    public static final String LI_REGISTER_OPEN="<li class=\"user-register\"><i class=\"fa fa-edit text-primary \"></i> <a href=\"register.do\" class=\"text-uppercase\">";
    public static final String INDEX_LOGIN= "index.login";
    public static final String LI_LOGIN_OPEN="<li class=\"user-login\"><i class=\"fa fa-sign-in text-primary\"></i> <a href=\"login.do\" class=\"text-uppercase\">";
    public static final String LI_REGISTER_LOGIN_CLOSE="</a></li>";

    public static final String LI_OPEN = "<li class=\"text-uppercase\">";
    public static final String LI_CLOSE = "</li>";
    public static final String LOGOUT = "<form action=\"logout.do\" id=\"logout-form\"method=\"POST\"><li class=\"user-login\"><i class=\"fa fa-sign-out text-primary\"></i>" +
            "<button type =\"submit\" id=\"logout\">" +
            "<a  class=\"text-uppercase\">index.logout</a></button></li>";
    public static final String INDEX_LOGOUT = "index.logout";
}
