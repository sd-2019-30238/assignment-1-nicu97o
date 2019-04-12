package com.bestFurnitureDeals.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;

    @PositiveOrZero(message = "Selected quantity must be greater or equal to zero")
    private int selectedQuantity;

    @PositiveOrZero(message = "Price must be greater or equal to zero")
    private BigDecimal price;

    private DealDTO deal;
}
