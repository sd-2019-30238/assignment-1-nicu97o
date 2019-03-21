package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.UserDAO;
import com.tuturugaNicolae.bestFurnitureDeals.exception.NoUserFoundException;
import com.tuturugaNicolae.bestFurnitureDeals.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class SecurityContext {
    private ThreadLocal<User> loggedUser;
    private UserDAO userDAO;

    @Autowired
    public SecurityContext(UserDAO userDAO) {
        this.userDAO = userDAO;
        this.loggedUser = new ThreadLocal<>();
    }

    @Transactional
    public void authenticate(String username, String password) {
        Optional<User> user = userDAO.findByUsername(username);
        if (!user.isPresent()) {
            throw new NoUserFoundException();
        }
        if (user.get().getPassword().equals(password)) {
            loggedUser.set(user.get());
        }
    }

    public void logout() {
        loggedUser.remove();
    }

    public ThreadLocal<User> getLoggedUser() {
        return loggedUser;
    }
}
