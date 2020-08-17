package com.plekhanov.servlet.logout;

import com.plekhanov.constant.Paths;
import com.plekhanov.constant.listener.ListenerConstants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * servlet that logout user
 */
@WebServlet(Paths.SLASH + Paths.LOGOUT_SERVLET)
public class Logout extends HttpServlet {
    /**
     * logout user
     *
     * @param httpServletRequest  - {@link HttpServletRequest}
     * @param httpServletResponse - {@link HttpServletResponse}
     * @throws IOException -  resp.sendRedirect throw
     */
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        if (httpServletRequest.getSession().getAttribute(ListenerConstants.CURRENT_USER) != null) {
            httpServletRequest.getSession().setAttribute(ListenerConstants.CURRENT_USER, null);
        }
        httpServletResponse.sendRedirect(Paths.INDEX_PAGE);
    }
}
