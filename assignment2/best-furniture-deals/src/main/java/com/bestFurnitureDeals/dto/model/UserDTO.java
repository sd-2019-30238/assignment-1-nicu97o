package com.bestFurnitureDeals.dto.model;

import com.bestFurnitureDeals.model.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;

    @NotBlank
    @Size(max = 45)
    private String username;

    @Column
    @NotBlank
    private String password;

    @NotBlank
    @Size(max = 45)
    private String mail;

    private UserType userType;
}
