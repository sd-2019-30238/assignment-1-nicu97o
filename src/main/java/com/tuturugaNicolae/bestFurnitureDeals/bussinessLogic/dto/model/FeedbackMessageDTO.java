package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FeedbackMessageDTO {
    private Long id;
    private String title;
    private String messageBody;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private OrderHistoryDTO orderHistoryDTO;
}
