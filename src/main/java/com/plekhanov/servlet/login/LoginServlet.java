package com.plekhanov.servlet.login;

import com.plekhanov.entity.user.User;
import com.plekhanov.service.UserService;
import com.plekhanov.servlet.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.plekhanov.constant.listener.ListenerConstants.CURRENT_USER;
import static com.plekhanov.constant.listener.ListenerConstants.REQUESTED_PAGE;
import static com.plekhanov.constant.servlet.login.LoginServletConstants.ERROR_MASSAGE_INCORRECT_USER;
import static com.plekhanov.constant.servlet.login.LoginServletConstants.ERROR_MASSAGE_UNREGISTERED_USER;
import static com.plekhanov.constant.Paths.*;
import static com.plekhanov.constant.servlet.register.RegisterServletConstants.ERRORS;
import static com.plekhanov.constant.user.UserConstants.EMAIL;
import static com.plekhanov.constant.user.UserConstants.PASSWORD;

/**
 * servlet that handles {@link User} authorization
 */
@WebServlet(SLASH + LOGIN_SERVLET)
public class LoginServlet extends HttpServlet {
    private Map<String, String> errors;

    /**
     * print errors and forward to login page
     *
     * @param req  - {@link HttpServletRequest}
     * @param resp - {@link HttpServletResponse}
     * @throws ServletException - req.getRequestDispatcher.forward() throws
     * @throws IOException      - req.getRequestDispatcher.forward() throws
     */
    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        errors = (Map<String, String>) req.getSession().getAttribute(ERRORS);
        if (errors != null) {
            errors.forEach(req::setAttribute);
        }
        req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
    }

    /**
     * handles user input
     *
     * @param req  - {@link HttpServletRequest}
     * @param resp - {@link HttpServletResponse}
     * @throws IOException -  resp.sendRedirect throw
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        errors = new HashMap<>();
        UserService userService = new UserService(req.getServletContext());
        String email = req.getParameter(EMAIL);
        User user;
        if ((user = userService.getUserByEmail(email)) == null) {
            errors.put(EMAIL, ERROR_MASSAGE_UNREGISTERED_USER);

        } else if (!Validator.comparePasswords(user.getPassword(), req.getParameter(PASSWORD))) {
            errors.put(PASSWORD, ERROR_MASSAGE_INCORRECT_USER);
        }
        if (errors.isEmpty()) {
            req.getSession().setAttribute(CURRENT_USER, user);
            redirectToPage(req, resp);
            return;
        }
        req.getSession().setAttribute(ERRORS, errors);
        resp.sendRedirect(LOGIN_SERVLET);
    }

    /**
     * @param req  - {@link HttpServletRequest}
     * @param resp - {@link HttpServletResponse}
     * @throws IOException - req.getRequestDispatcher.forward() throws
     */
    private void redirectToPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestedPage = (String) req.getSession().getAttribute(REQUESTED_PAGE);

        if (requestedPage != null) {
            req.getSession().setAttribute(REQUESTED_PAGE, null);
            resp.sendRedirect(requestedPage);
        } else {
            resp.sendRedirect(INDEX_PAGE);
        }
    }
}
