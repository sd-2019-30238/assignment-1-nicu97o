package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.UserService;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.UserDAO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.User;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.exception.BadCredentialsException;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.exception.NoRightsToPerformThisOperationException;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.exception.NoUserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User getUserById(long id) {
        User user = userDAO.selectById(id);
        if (user == null) {
            throw new NoUserFoundException("No user with id " + id + " found!");
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> user = userDAO.findByUsername(username);
        if (!user.isPresent()) {
            throw new NoUserFoundException("No user with username " + username + " found!");
        }
        return user.get();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userDAO.selectAll();
        if (users.isEmpty()) {
            throw new NoUserFoundException();
        }
        return users;
    }

    @Override
    public void updateUser(User user, String usernameOfLoggedUser) {
        if (!user.getUsername().equals(usernameOfLoggedUser)) {
            throw new NoRightsToPerformThisOperationException();
        }
        User oldUser = getUserByUsername(usernameOfLoggedUser);
        oldUser.setMail(user.getMail());
        oldUser.setUserType(user.getUserType());
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        userDAO.update(oldUser);
    }

    @Override
    public void deleteUser(String username) {
        User user = getUserByUsername(username);
        userDAO.delete(user);
    }

    @Override
    public void addUser(User user, String password) {
        if (!user.getPassword().equals(password)) {
            throw new BadCredentialsException();
        }
        user.setId(0L);
        userDAO.insert(user);
    }
}
