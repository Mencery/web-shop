package com.plekhanov.service;

import com.plekhanov.db.UsersDAO;
import com.plekhanov.entity.user.User;
import com.plekhanov.repository.UserRepository;

import javax.servlet.ServletContext;

import java.sql.SQLException;

import static com.plekhanov.constant.listener.ListenerConstants.*;

/**
 * service for {@link User}
 */
public class UserService {
    private UserRepository userRepository;
    private ServletContext servletContext;

    public UserService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /**
     * check contains {@link User} in {@link UserRepository}
     *
     * @param user - user that checks
     * @return true if user contains
     */
    public boolean contains(User user) {
        userRepository = (UserRepository) servletContext.getAttribute(USERS);
        return userRepository.contains(user);
    }

    /**
     * get {@link User} from database by email
     *
     * @param email - user id
     * @return found user or null
     */
    public User getUserByEmail(String email) {
        User user = null;
        try {
            UsersDAO dao = (UsersDAO) servletContext.getAttribute(CONTEXT_USERS_DAO);
            user = dao.getById(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * add {@link User} in repository
     *
     * @param user - that adding
     */
    public void add(User user) {
        userRepository.add(user);
    }

    /**
     * add {@link User} in database
     *
     * @param user - that adding
     */
    public void addToDB(User user) {
        try {
            UsersDAO dao = (UsersDAO) servletContext.getAttribute(CONTEXT_USERS_DAO);
            dao.add(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
