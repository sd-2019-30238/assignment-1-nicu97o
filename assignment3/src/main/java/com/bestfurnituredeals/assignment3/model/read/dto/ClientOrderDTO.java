package com.bestfurnituredeals.assignment3.model.read.dto;

import com.bestfurnituredeals.assignment3.model.database.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientOrderDTO {
    private Long id;

    private boolean approved;

    private PaymentMethod paymentMethod;

    private BigDecimal totalPrice;

    private boolean finished;

    private List<ProductDTO> products;

    private OrderHistoryDTO orderHistory;

    private UserDTO user;
}
