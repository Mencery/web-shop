package com.plekhanov.tag.logintag;

import com.plekhanov.entity.user.User;
import com.plekhanov.constant.filter.LocaleFilterConstants;
import com.plekhanov.constant.listener.ListenerConstants;
import com.plekhanov.constant.servlet.avatar.AvatarConstants;
import com.plekhanov.constant.servlet.login.LoginServletConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * handles login tag
 */
public class LoginTag extends SimpleTagSupport {
    /**
     * print avatar on page if user authorized  else print register and login link
     */
    public void doTag() {
        try {
            PageContext pc = (PageContext) getJspContext();
            JspWriter jspWriter = getJspContext().getOut();
            HttpServletRequest request = (HttpServletRequest) pc.getRequest();
            User user = (User) request.getSession().getAttribute(ListenerConstants.CURRENT_USER);
            ResourceBundle bundle = ResourceBundle.getBundle(LocaleFilterConstants.RESOURCES, request.getLocale());
            if (user == null) {
                jspWriter.println(LoginServletConstants.LI_REGISTER_OPEN);
                jspWriter.println(bundle.getString(LoginServletConstants.INDEX_REGISTER));
                jspWriter.println(LoginServletConstants.LI_REGISTER_LOGIN_CLOSE);

                jspWriter.println(LoginServletConstants.LI_LOGIN_OPEN);
                jspWriter.println(bundle.getString(LoginServletConstants.INDEX_LOGIN));
                jspWriter.println(LoginServletConstants.LI_REGISTER_LOGIN_CLOSE);
            } else {
                jspWriter.print(LoginServletConstants.LI_OPEN);
                jspWriter.print(user.getName() + " " + user.getSurname());
                jspWriter.println(LoginServletConstants.LI_CLOSE);
                jspWriter.println(
                        LoginServletConstants.LOGOUT.replaceAll(LoginServletConstants.INDEX_LOGOUT,bundle.getString(LoginServletConstants.INDEX_LOGOUT) ));

                jspWriter.print(AvatarConstants.DIV_OPEN);
                jspWriter.print(AvatarConstants.IMG_AVATAR);
                jspWriter.print(AvatarConstants.CHOOSE_FILE);
                jspWriter.print(AvatarConstants.DIV_CLOSE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
