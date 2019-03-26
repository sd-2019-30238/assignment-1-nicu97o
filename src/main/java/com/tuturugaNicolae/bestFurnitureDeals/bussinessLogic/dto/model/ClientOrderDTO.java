package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ClientOrderDTO {
    private Long id;
    private boolean approved;
    private PaymentMethodDTO paymentMethodDTO;
    private BigDecimal totalPrice;
    private boolean finished;
    @ToString.Exclude
    private UserDTO client;
}
