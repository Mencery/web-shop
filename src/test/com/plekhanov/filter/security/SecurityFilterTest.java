package com.plekhanov.filter.security;

import com.plekhanov.entity.user.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.plekhanov.constant.Paths.ERROR_403_PAGE;
import static com.plekhanov.constant.Paths.LOGIN_SERVLET;
import static com.plekhanov.constant.listener.ListenerConstants.CURRENT_USER;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SecurityFilterTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain filterChain;
    @Mock
    private FilterConfig filterConfig;
    @Mock
    private HttpSession session;
    private SecurityFilter securityFilter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        securityFilter = new SecurityFilter();
        securityFilter.init(filterConfig);
    }

    @Test
    public void shouldCallDoFilter_whenPageNoPermission() throws IOException, ServletException {
        //given
        StringBuffer stringBuffer= new StringBuffer();
       when(request.getRequestURL()).thenReturn(stringBuffer);
       //when
       securityFilter.doFilter(request, response, filterChain);
       //then
        verify(filterChain).doFilter(any(), any());

    }
    @Test
    public void shouldRedirectToLoginPage_whenUserAnLoggedIn() throws IOException, ServletException {
        //given
        StringBuffer stringBuffer= new StringBuffer("shop/cart/");
        when(request.getRequestURL()).thenReturn(stringBuffer);
        when(request.getSession()).thenReturn(session);
        //when
        securityFilter.doFilter(request, response, filterChain);
        //then
        verify(response).sendRedirect(LOGIN_SERVLET);

    }
    @Test
    public void shouldCallDoFilter_whenUserHasPermission() throws IOException, ServletException {
        //given
        StringBuffer stringBuffer= new StringBuffer("shop/cart/");
        when(request.getRequestURL()).thenReturn(stringBuffer);
        when(request.getSession()).thenReturn(session);
        User user = new User();
        user.setRole("user");
        when(session.getAttribute(CURRENT_USER)).thenReturn(user);
        //when
        securityFilter.doFilter(request, response, filterChain);
        //then
        verify(filterChain).doFilter(any(), any());

    }
    @Test
    public void shouldRedirectTo403Page_whenUserDoesntHavePermission() throws IOException, ServletException {
        //given
        StringBuffer stringBuffer= new StringBuffer("shop/cart/");
        when(request.getRequestURL()).thenReturn(stringBuffer);
        when(request.getSession()).thenReturn(session);
        User user = new User();
        user.setRole("admin");
        when(session.getAttribute(CURRENT_USER)).thenReturn(user);
        //when
        securityFilter.doFilter(request, response, filterChain);
        //then
        verify(response).sendRedirect(ERROR_403_PAGE);

    }
    @After
    public void destroy(){
        securityFilter.destroy();
    }
}