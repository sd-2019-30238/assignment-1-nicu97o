package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.User;
import com.tuturugaNicolae.bestFurnitureDeals.exception.BadCredentialsException;
import com.tuturugaNicolae.bestFurnitureDeals.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SecurityContext {
    private ThreadLocal<User> loggedUser = new ThreadLocal<>();
    private UserService userService;

    @Autowired
    public SecurityContext(UserService userService) {
        this.userService = userService;
    }

    public void authenticate(String username, String password) {
        User user = userService.getUserByUsername(username);
        if (!user.getPassword().equals(password)) {
            throw new BadCredentialsException();
        }
        loggedUser.set(user);
    }

    public void logout() {
        loggedUser.remove();
    }

    public ThreadLocal<User> getLoggedUser() {
        return loggedUser;
    }

    public boolean isAuthenticated() {
        if (loggedUser.get() != null) {
            return true;
        }
        return false;
    }

    public String getNameOfAuthenticatedUser() {
        return loggedUser.get().getUsername();
    }
}
