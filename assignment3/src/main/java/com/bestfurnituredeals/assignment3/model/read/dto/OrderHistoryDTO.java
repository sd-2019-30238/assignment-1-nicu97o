package com.bestfurnituredeals.assignment3.model.read.dto;

import com.bestfurnituredeals.assignment3.model.database.OrderState;
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
