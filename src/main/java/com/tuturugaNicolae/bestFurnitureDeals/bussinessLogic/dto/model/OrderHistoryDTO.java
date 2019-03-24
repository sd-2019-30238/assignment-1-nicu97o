package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderHistoryDTO {
    private Long id;
    private ClientOrderDTO clientOrderDTO;
    private LocalDateTime orderPlaceDateTime;
    private OrderStateDTO orderStateDTO;
}
