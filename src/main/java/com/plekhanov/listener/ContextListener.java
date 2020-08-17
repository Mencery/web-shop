package com.plekhanov.listener;

import com.plekhanov.db.OrderDAO;
import com.plekhanov.db.OrderedProductDAO;
import com.plekhanov.db.ProductDAO;
import com.plekhanov.db.UsersDAO;
import com.plekhanov.repository.UserRepository;
import com.plekhanov.service.CartService;
import com.plekhanov.service.ProductService;
import com.plekhanov.servlet.captcha.strategy.factory.CaptchaStrategyFactory;
import com.plekhanov.servlet.captcha.strategy.scope.CaptchaStrategyScope;
import com.plekhanov.constant.listener.ListenerConstants;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * listener that contains server context
 */
public class ContextListener implements ServletContextListener {
    /**
     * initialization
     *
     * @param servletContextEvent - servlet context event
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        setStrategyFactory(servletContext);
        setUserRepository(servletContext);
        setUsersDao(servletContext);
        setProductsDao(servletContext);
        setOrderDAO(servletContext);
        setOrderedProductDAO(servletContext);
        setProductService(servletContext);
        setCartService(servletContext);
    }

    /**
     * destroy context
     *
     * @param sce - servlet context event
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    /**
     * @param servletContext - context in that setting user repository
     */
    private void setStrategyFactory(ServletContext servletContext) {
        CaptchaStrategyFactory strategyFactory = new CaptchaStrategyFactory();
        CaptchaStrategyScope scope = CaptchaStrategyScope
                .valueOf(servletContext.getInitParameter(ListenerConstants.CAPTCHA_SCOPE).toUpperCase());
        setContextCaptchaMap(servletContext);
        servletContext.setAttribute(ListenerConstants.CAPTCHA_STRATEGY,
                strategyFactory.getStrategy(scope));
    }

    /**
     * set user repository
     *
     * @param servletContext - context in that setting user repository
     */
    private void setUserRepository(ServletContext servletContext) {
        UserRepository userRepository = new UserRepository();
        userRepository.fillUsers();
        servletContext.setAttribute(ListenerConstants.USERS, userRepository);
    }

    /**
     * set captcha map
     *
     * @param servletContext - context in that setting captcha map
     */
    private void setContextCaptchaMap(ServletContext servletContext) {
        ConcurrentMap<AtomicLong, String> contextCaptchaMap = new ConcurrentHashMap<>();
        servletContext.setAttribute(ListenerConstants.CONTEXT_CAPTCHA_MAP, contextCaptchaMap);
    }

    /**
     * set users dao
     *
     * @param servletContext - context in that setting users dao
     */
    private void setUsersDao(ServletContext servletContext) {
        UsersDAO usersDAO = new UsersDAO();
        servletContext.setAttribute(ListenerConstants.CONTEXT_USERS_DAO, usersDAO);
    }

    /**
     * set {@link ProductDAO}
     * @param servletContext - context in that {@link ProductDAO}
     */
    private void setProductsDao(ServletContext servletContext){
        ProductDAO productDAO = new ProductDAO();
        servletContext.setAttribute(ListenerConstants.CONTEXT_PRODUCTS_DAO, productDAO);
    }
    /**
     * set {@link OrderDAO}
     * @param servletContext - context in that {@link OrderDAO}
     */
    private void setOrderDAO(ServletContext servletContext){
        OrderDAO orderDAO = new OrderDAO();
        servletContext.setAttribute(ListenerConstants.CONTEXT_ORDER_DAO, orderDAO);
    }
    /**
     * set {@link OrderedProductDAO}
     * @param servletContext - context in that {@link OrderedProductDAO}
     */
    private void setOrderedProductDAO(ServletContext servletContext){
        OrderedProductDAO orderDAO = new OrderedProductDAO();
        servletContext.setAttribute(ListenerConstants.CONTEXT_ORDERED_PRODUCT_DAO, orderDAO);
    }
    /**
     *set {@link ProductService}
     * @param servletContext - context in that {@link ProductService}
     */
    private void setProductService(ServletContext servletContext){
        ProductService productService = new ProductService(servletContext);
        servletContext.setAttribute(ListenerConstants.CONTEXT_PRODUCT_SERVICE, productService);
    }
    /**
     *set {@link CartService}
     * @param servletContext - context in that {@link ProductService}
     */
    private void setCartService(ServletContext servletContext){
        CartService cartService = new CartService(servletContext);
        servletContext.setAttribute(ListenerConstants.CONTEXT_CART_SERVICE, cartService);
    }
}
