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
    private ThreadLocal<UserDTO> loggedUser = new ThreadLocal<>();
    private UserDAO userDAO;
    private Mapper<User, UserDTO> userMapper;

    @Autowired
    public SecurityContext(UserDAO userDAO, Mapper<User, UserDTO> userMapper) {
        this.userDAO = userDAO;
        this.userMapper = userMapper;
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
        loggedUser.set(userMapper.convertToDTO(user.get()));
    }

    public void logout() {
        loggedUser.remove();
    }

    public ThreadLocal<UserDTO> getLoggedUser() {
        return loggedUser;
    }

    public boolean isAuthenticated() {
        if (loggedUser.get() != null) {
            return true;
        }
        return false;
    }
}
