package com.plekhanov.servlet.register;

import com.plekhanov.db.UsersDAO;
import com.plekhanov.repository.UserRepository;
import com.plekhanov.servlet.captcha.strategy.SessionCaptchaStrategy;
import com.plekhanov.constant.Paths;
import com.plekhanov.constant.listener.ListenerConstants;
import com.plekhanov.constant.servlet.captcha.CaptchaConstants;
import com.plekhanov.constant.servlet.register.RegisterServletConstants;
import com.plekhanov.constant.user.UserConstants;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static org.mockito.Mockito.*;


public class RegisterServletTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RegisterServlet servlet;
    private RequestDispatcher requestDispatcher;
    private ServletContext servletContext;
    private HttpSession session;
    private ConcurrentHashMap<AtomicLong, String> map;
    private String captchaValid = "captcha";

    @Before
    public void init() {
        servlet = new RegisterServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
        servletContext = mock(ServletContext.class);
        session = mock(HttpSession.class);
        map = new ConcurrentHashMap<>();
        UsersDAO dao = new UsersDAO();
        when(servletContext.getAttribute(ListenerConstants.CONTEXT_USERS_DAO)).thenReturn(dao);
    }

    public void setupDoPost() {
        UserRepository userRepository = new UserRepository();
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute(ListenerConstants.USERS)).thenReturn(userRepository);
        when(servletContext.getInitParameter(ListenerConstants.CAPTCHA_SCOPE)).thenReturn("session");
        when(request.getSession()).thenReturn(session);
        when(servletContext.getAttribute(ListenerConstants.CONTEXT_CAPTCHA_MAP)).thenReturn(map);
        int i = 1;
        map.put(new AtomicLong(i), captchaValid);
        when(session.getAttribute(CaptchaConstants.CAPTCHA_NUMBER)).thenReturn(i);

        when(servletContext.getAttribute(ListenerConstants.CAPTCHA_STRATEGY)).thenReturn(new SessionCaptchaStrategy());
        fillUser();
    }

    @Test
    public void shouldForwardToRegisterPage() throws ServletException, IOException {
        when(request.getRequestDispatcher(Paths.REGISTER_PAGE)).thenReturn(requestDispatcher);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(RegisterServletConstants.ERRORS)).thenReturn(null);
        servlet.doGet(request, response);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void shouldRedirectToIndexPage_whenUserInputInvalidCaptcha() throws IOException {
        setupDoPost();
        when(request.getParameter(CaptchaConstants.USER_INPUT_CAPTCHA)).thenReturn(captchaValid);
        servlet.doPost(request, response);
        verify(response).sendRedirect("register.do");
    }

    @Test
    public void shouldRedirectToRegisterServlet_whenUserInputInvalidCaptcha() throws IOException {
        setupDoPost();
        String captchaInvalid = "captchaInvalid";
        when(request.getParameter(CaptchaConstants.USER_INPUT_CAPTCHA)).thenReturn(captchaInvalid);
        servlet.doPost(request, response);
        verify(response).sendRedirect(Paths.REGISTER_SERVLET);
    }

    private void fillUser() {
        when(request.getParameter(UserConstants.NAME)).thenReturn("Ghh");
        when(request.getParameter(UserConstants.SURNAME)).thenReturn("Ggg");
        when(request.getParameter(UserConstants.PASSWORD)).thenReturn("1111111");
        when(request.getParameter(UserConstants.EMAIL)).thenReturn("ghg@hh.com");
    }
}