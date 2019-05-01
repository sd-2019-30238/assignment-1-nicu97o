package com.bestfurnituredeals.assignment3.model.read.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackMessageDTO {
    private Long id;

    private String title;

    private String messageBody;

    private OrderHistoryDTO orderHistory;
}
