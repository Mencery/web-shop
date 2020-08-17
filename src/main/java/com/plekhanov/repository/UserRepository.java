package com.plekhanov.repository;

import com.plekhanov.entity.user.User;
import com.plekhanov.constant.user.UserRepositoryConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link User} repository where users stores constantly
 */
public class UserRepository {
    List<User> users;

    /**
     * constructor that initialization  List<User> users
     */
    public UserRepository() {
        users = new ArrayList<>();
    }

    /**
     * fill list with constant users
     */
    public void fillUsers() {
        users.add(new User(UserRepositoryConstants.USER1_NAME, UserRepositoryConstants.USER1_SURNAME, UserRepositoryConstants.USER1_EMAIL, UserRepositoryConstants.USER1_PASSWORD, true));
        users.add(new User(UserRepositoryConstants.USER2_NAME, UserRepositoryConstants.USER2_SURNAME, UserRepositoryConstants.USER2_EMAIL, UserRepositoryConstants.USER2_PASSWORD, false));
    }

    /**
     * check {@link User} is contains
     *
     * @param user - that is checked
     * @return true if contains
     */
    public boolean contains(User user) {
        return users.contains(user);
    }

    /**
     * add {@link User} to list
     *
     * @param user - user that is added
     */
    public void add(User user) {
        users.add(user);
    }
}
