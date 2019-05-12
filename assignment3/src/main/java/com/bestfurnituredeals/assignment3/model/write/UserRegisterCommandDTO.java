package com.bestfurnituredeals.assignment3.model.write;

import com.bestfurnituredeals.assignment3.model.database.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRegisterCommandDTO {
    @NotBlank
    @Size(max = 45)
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @Size(max = 45)
    private String mail;

    private UserType userType;
}
