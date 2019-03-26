package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FeedbackMessageDTO {
    private Long id;
    private String title;
    private String messageBody;
    @ToString.Exclude
    private OrderHistoryDTO orderHistoryDTO;
}
