package com.bestFurnitureDeals.dto.model;

import com.bestFurnitureDeals.model.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientOrderDTO {
    private Long id;

    private boolean approved;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull(message = "Price can't be null")
    @PositiveOrZero(message = "Price needs to be greater or equal to 0")
    private BigDecimal totalPrice;

    private boolean finished;

    private List<ProductDTO> products;

    private OrderHistoryDTO orderHistory;

    private UserDTO user;
}
