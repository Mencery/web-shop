package com.plekhanov.servlet.register;

import com.plekhanov.entity.user.User;
import com.plekhanov.service.UserService;
import com.plekhanov.servlet.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.plekhanov.constant.Paths.*;
import static com.plekhanov.constant.user.UserConstants.*;
import static com.plekhanov.constant.servlet.register.RegisterServletConstants.*;

/**
 * servlet that handles {@link User} registration
 */
@WebServlet(SLASH + REGISTER_SERVLET)
public class RegisterServlet extends HttpServlet {
    private Map<String, String> errors;

    /**
     * print errors and forward to register page
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
        req.getRequestDispatcher(REGISTER_PAGE).forward(req, resp);
    }

    /**
     * handles {@link User} input
     *
     * @param req  - {@link HttpServletRequest}
     * @param resp - {@link HttpServletResponse}
     * @throws IOException -  resp.sendRedirect throw
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        errors = new HashMap<>();
        UserService userService = new UserService(req.getServletContext());
        User user = new User();
        UserFactory userFactory = new UserFactory(user);

        fillUser(userFactory, req);

        Validator.validateCaptcha(req, errors);
        if (userService.getUserByEmail(user.getEmail()) != null) {
            errors.put(EMAIL, ERROR_MASSAGE_SUCH_USER_EXISTS);
        }
        if (errors.isEmpty()) {
            userService.addToDB(user);
            resp.sendRedirect(INDEX_PAGE);
            return;
        }
        req.getSession().setAttribute(ERRORS, errors);
        fillCookies(userFactory, resp);
        resp.sendRedirect(REGISTER_SERVLET);
    }

    /**
     * fill cookies with {@link User} parameters
     *
     * @param userFactory - {@link UserFactory}
     * @param resp        - {@link HttpServletResponse}
     */
    private void fillCookies(UserFactory userFactory, HttpServletResponse resp) {
        for (String paramName : userFactory.getKeySet()) {
            Cookie cookie = new Cookie(paramName, userFactory.getParameter(paramName));
            cookie.setMaxAge(MAX_AGE_COOKIE);
            resp.addCookie(cookie);
        }
    }

    /**
     * fill {@link User} with input parameters
     *
     * @param userFactory - {@link UserFactory}
     * @param req         - {@link HttpServletRequest}
     */
    private void fillUser(UserFactory userFactory, HttpServletRequest req) {
        for (String paramName : userFactory.getKeySet()) {
            String parameter = req.getParameter(paramName);
            Validator.validateField(errors, paramName, parameter, userFactory.getRegex(paramName));
            userFactory.setParameter(paramName, parameter);
        }
        userFactory.setMailings(Validator.isMailings(req.getParameterValues(MAILING)));
    }
}
