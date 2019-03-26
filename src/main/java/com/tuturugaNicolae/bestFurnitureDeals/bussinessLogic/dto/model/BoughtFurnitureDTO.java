package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

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
    @ToString.Exclude
    private ClientOrderDTO clientOrderDTO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoughtFurnitureDTO that = (BoughtFurnitureDTO) o;
        return boughtQuantity == that.boughtQuantity &&
                Objects.equals(id, that.id) &&
                price.compareTo(that.price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, boughtQuantity, price);
    }
}
