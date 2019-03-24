package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.DealType;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DealDTO {
    private Long id;
    private String name;
    private DealType dealType;
    private FurnitureDTO furnitureDTO;
    private BigDecimal price;
    private boolean available;
    private int availableQuantity;
}
