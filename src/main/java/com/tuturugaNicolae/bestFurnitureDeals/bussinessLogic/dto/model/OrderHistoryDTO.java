package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class OrderHistoryDTO {
    private Long id;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ClientOrderDTO clientOrderDTO;
    private LocalDateTime orderPlaceDateTime;
    private OrderStateDTO orderStateDTO;
}
