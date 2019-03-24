package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.UserDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.Mapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.UserTypeDTO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.User;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.UserType;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserDTO> {
    @Override
    public User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setMail(userDTO.getMail());
        user.setUserType(UserType.valueOf(userDTO.getUserTypeDTO().toString()));
        return user;
    }

    @Override
    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setMail(user.getMail());
        userDTO.setUserTypeDTO(UserTypeDTO.valueOf(user.getUserType().toString()));
        return userDTO;
    }
}
