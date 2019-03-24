package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DealDTO {
    private Long id;
    private String name;
    private DealTypeDTO dealTypeDTO;
    private FurnitureDTO furnitureDTO;
    private BigDecimal price;
    private boolean available;
    private int availableQuantity;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", dealTypeDTO=").append(dealTypeDTO);
        sb.append(", furnitureDTO=").append(furnitureDTO);
        sb.append(", price=").append(price);
        sb.append(", available=").append(available);
        sb.append(", availableQuantity=").append(availableQuantity);
        return sb.toString();
    }
}
