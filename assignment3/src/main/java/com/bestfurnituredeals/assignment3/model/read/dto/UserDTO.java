package com.bestfurnituredeals.assignment3.model.read.dto;

import com.bestfurnituredeals.assignment3.model.database.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;

    private String username;

    private String password;

    private String mail;

    private UserType userType;
}
