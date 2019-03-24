package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.PaymentMethod;
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
    private PaymentMethod paymentMethod;
    private BigDecimal totalPrice;
    private boolean finished;
    private UserDTO client;
}
