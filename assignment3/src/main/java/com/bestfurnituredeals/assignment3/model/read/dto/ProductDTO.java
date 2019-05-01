package com.bestfurnituredeals.assignment3.model.read.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;

    private int selectedQuantity;

    private BigDecimal price;

    private DealDTO deal;
}
