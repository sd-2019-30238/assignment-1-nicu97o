package com.bestfurnituredeals.assignment3.model.read.mapper.impl;

import com.bestfurnituredeals.assignment3.model.database.User;
import com.bestfurnituredeals.assignment3.model.read.mapper.Mapper;
import com.bestfurnituredeals.assignment3.model.read.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserDTO> {
    @Override
    public User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        user.setMail(userDTO.getMail());
        user.setUserType(userDTO.getUserType());
        return user;
    }

    @Override
    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setPassword(user.getPassword());
        userDTO.setUsername(user.getUsername());
        userDTO.setMail(user.getMail());
        userDTO.setUserType(user.getUserType());
        return userDTO;
    }
}
