package com.bestfurnituredeals.assignment3.model.write;

import com.bestfurnituredeals.assignment3.model.database.DealType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DealAddCommandDTO {
    @NotBlank(message = "Name can't be null")
    @Size(min = 3, max = 45, message = "Invalid length")
    private String name;

    @NotNull(message = "Deal type can't be null")
    private DealType dealType;

    @NotNull(message = "Price can't be null")
    @PositiveOrZero(message = "Price needs to be greater or equal to 0")
    private BigDecimal price;

    @PositiveOrZero
    @PositiveOrZero(message = "Quantity needs to be greater or equal to 0")
    private int availableQuantity;

    private boolean available;

    private long furnitureId;
}
