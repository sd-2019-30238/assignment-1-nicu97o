package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.UserDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.Mapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.UserService;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.Validator;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.UserDAO;
import com.tuturugaNicolae.bestFurnitureDeals.exception.InvalidUserException;
import com.tuturugaNicolae.bestFurnitureDeals.exception.NoRightsToPerformThisOperationException;
import com.tuturugaNicolae.bestFurnitureDeals.exception.NoUserFoundException;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    private Validator<UserDTO> userValidator;
    private Mapper<User, UserDTO> userMapper;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, Validator<UserDTO> userValidator, Mapper<User, UserDTO> userMapper) {
        this.userDAO = userDAO;
        this.userValidator = userValidator;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO getUserById(long id) {
        User user = getUserEntityById(id);
        return userMapper.convertToDTO(user);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = getUserEntityByUsername(username);
        return userMapper.convertToDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userDAO.selectAll();
        if (users.isEmpty()) {
            throw new NoUserFoundException();
        }
        return users.stream().map(userMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void updateUser(UserDTO userDTO, String usernameOfLoggedUser) {
        User user = userMapper.convertToEntity(userDTO);
        if (!userValidator.validate(userDTO)) {
            throw new InvalidUserException();
        }
        if (!user.getUsername().equals(usernameOfLoggedUser)) {
            throw new NoRightsToPerformThisOperationException();
        }
        User oldUser = getUserEntityByUsername(usernameOfLoggedUser);
        oldUser.setMail(user.getMail());
        oldUser.setUserType(user.getUserType());
        oldUser.setUsername(userDTO.getUsername());
        userDAO.update(oldUser);
    }

    @Override
    public void deleteUser(String username) {
        User user = getUserEntityByUsername(username);
        userDAO.delete(user);
    }

    @Override
    public void addUser(UserDTO userDTO, String password) {
        if (!userValidator.validate(userDTO) || password == null || password.length() < 5) {
            throw new InvalidUserException();
        }
        User user = userMapper.convertToEntity(userDTO);
        user.setId(0L);
        user.setPassword(password);
        userDAO.insert(user);
    }

    private User getUserEntityByUsername(String username) {
        Optional<User> user = userDAO.findByUsername(username);
        if (!user.isPresent()) {
            throw new NoUserFoundException("No user with username " + username + " found!");
        }
        return user.get();
    }

    private User getUserEntityById(long id) {
        User user = userDAO.selectById(id);
        if (user == null) {
            throw new NoUserFoundException("No user with id " + id + " found!");
        }
        return user;
    }
}
