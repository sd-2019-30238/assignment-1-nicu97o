package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.UserType;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    private String username;
    private String mail;
    private UserType userType;
}
