package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.UserDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.Mapper;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.UserDAO;
import com.tuturugaNicolae.bestFurnitureDeals.exception.BadCredentialsException;
import com.tuturugaNicolae.bestFurnitureDeals.exception.NoUserFoundException;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.User;
import com.tuturugaNicolae.bestFurnitureDeals.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class SecurityContext {
    private ThreadLocal<User> loggedUser = new ThreadLocal<>();
    private UserDAO userDAO;

    @Autowired
    public SecurityContext(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public void authenticate(String username, String password) {
        Optional<User> user = userDAO.findByUsername(username);
        if (!user.isPresent()) {
            throw new NoUserFoundException();
        }
        if (!user.get().getPassword().equals(password)) {
            throw new BadCredentialsException();
        }
        loggedUser.set(user.get());
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
}
