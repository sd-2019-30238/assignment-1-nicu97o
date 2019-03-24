package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model;

import lombok.*;

import java.math.BigDecimal;

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
    private UserDTO client;
}
