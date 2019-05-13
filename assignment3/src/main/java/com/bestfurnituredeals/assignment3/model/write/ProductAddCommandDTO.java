package com.bestfurnituredeals.assignment3.model.write;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductAddCommandDTO {
    private long dealId;

    @PositiveOrZero
    private int quantity;

    @NotBlank
    private String buyersName;
}
