package com.bestFurnitureDeals.dto.model;

import com.bestFurnitureDeals.model.OrderState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistoryDTO {
    private Long id;

    private LocalDateTime orderPlaceDateTime;

    private OrderState orderState;
}
