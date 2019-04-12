package com.bestFurnitureDeals.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackMessageDTO {
    private Long id;

    @NotBlank
    @Size(min = 3)
    private String title;

    @NotBlank
    @Size(min = 5)
    private String messageBody;

    private OrderHistoryDTO orderHistory;
}
