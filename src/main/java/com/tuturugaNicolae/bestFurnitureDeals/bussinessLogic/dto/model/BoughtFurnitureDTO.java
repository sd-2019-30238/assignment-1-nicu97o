package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BoughtFurnitureDTO {
    private Long id;
    private FurnitureDTO furnitureDTO;
    private int boughtQuantity;
    private BigDecimal price;
    private ClientOrderDTO clientOrderDTO;
}
