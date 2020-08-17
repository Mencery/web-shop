package com.plekhanov.service;

import com.plekhanov.entity.user.Role;
import com.plekhanov.entity.user.User;
import com.plekhanov.filter.security.SecurityFilter;
import com.plekhanov.xmlparser.SecurityParser;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * layer between {@link SecurityFilter} and {@link SecurityParser}
 */
public class SecurityService {
    public Map<String, List<Role>> urlPattern2Roles;

    /**
     * initializes {@link SecurityParser} and parses
     */
    public SecurityService() {
        SecurityParser parser = new SecurityParser();
        urlPattern2Roles = parser.parse();
    }

    /**
     *
     * @param url - that checks
     * @return true if no permission page
     */
    public boolean isNoPermissionPage(String url){
        AtomicBoolean accessed = new AtomicBoolean(true);

        urlPattern2Roles.forEach((k, v)-> {
            if(url.matches(k)){
                accessed.set(false);
            }
        });
        return accessed.get();
    }
    /**
     *
     * @param url - that checks
     * @param user - user that checks
     * @return true if this user have permission on this page
     */
    public boolean isAccessForUser(String url, User user){
        AtomicBoolean accessed = new AtomicBoolean(true);
        urlPattern2Roles.forEach((k, v)-> {
            if(url.matches(k)){
                accessed.set(v.contains(user.getRole()));
            }
        });
        return accessed.get();
    }
}
