package com.bestFurnitureDeals.dto.model;

import com.bestFurnitureDeals.model.DealType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
public class DealDTO {
    private Long id;

    @NotBlank(message = "Name can't be null")
    @Size(min = 3, max = 45, message = "Invalid length")
    private String name;

    @NotNull(message = "Deal type can't be null")
    private DealType dealType;

    private FurnitureDTO furniture;

    @NotNull(message = "Price can't be null")
    @PositiveOrZero(message = "Price needs to be greater or equal to 0")
    private BigDecimal price;

    private boolean available;

    @PositiveOrZero
    @PositiveOrZero(message = "Quantity needs to be greater or equal to 0")
    private int availableQuantity;
}
