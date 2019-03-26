package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ClientOrderDTO {
    private Long id;
    private boolean approved;
    private PaymentMethodDTO paymentMethodDTO;
    private BigDecimal totalPrice;
    private boolean finished;
    @ToString.Exclude
    private UserDTO client;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientOrderDTO that = (ClientOrderDTO) o;
        return approved == that.approved &&
                finished == that.finished &&
                Objects.equals(id, that.id) &&
                paymentMethodDTO == that.paymentMethodDTO &&
                totalPrice.compareTo(that.totalPrice) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, approved, paymentMethodDTO, totalPrice, finished);
    }
}
