package com.plekhanov.entity.user;

import java.util.Objects;

/**
 * entity for users table
 */
public class User {
    private String name;
    private String surname;
    private String email;
    private String password;
    private boolean mailings;
    private String avatarPath;
    private Role role;

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    /**
     * empty constructor
     */
    public User() {
    }

    /**
     * constructor with parameters
     *
     * @param name     - user name
     * @param surname  - user surname
     * @param email    - user email
     * @param password - user password
     * @param mailings - if user want get mails
     */
    public User(String name, String surname, String email, String password, boolean mailings) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.mailings = mailings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isMailings() {
        return mailings;
    }

    public void setMailings(boolean mailings) {
        this.mailings = mailings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return email.equals(user.email);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = Role.valueOf(role.toUpperCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}

