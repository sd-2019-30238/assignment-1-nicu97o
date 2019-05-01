package com.bestfurnituredeals.assignment3.model.read.dto;

import com.bestfurnituredeals.assignment3.model.database.DealType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DealDTO {
    private Long id;

    private String name;

    private DealType dealType;

    private FurnitureDTO furniture;

    private BigDecimal price;

    private boolean available;

    private int availableQuantity;
}
