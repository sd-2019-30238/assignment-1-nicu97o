package com.bestfurnituredeals.assignment3.model.write;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutCommandDTO {
    @NotBlank
    private String username;

    private boolean subscribe;
}
