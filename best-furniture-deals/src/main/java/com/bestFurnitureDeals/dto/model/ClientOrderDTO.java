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

    public ClientOrderDTO(Long id, boolean approved, @NotNull PaymentMethod paymentMethod, @NotNull(message = "Price can't be null") @PositiveOrZero(message = "Price needs to be greater or equal to 0") BigDecimal totalPrice, boolean finished, UserDTO user) {
        this.id = id;
        this.approved = approved;
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
        this.finished = finished;
        this.user = user;
    }
}
