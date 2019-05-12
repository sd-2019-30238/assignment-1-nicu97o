package com.bestfurnituredeals.assignment3.model.write;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FeedbackMessagePostCommandDTO {
    @NotBlank
    @Size(min = 3)
    private String title;

    @NotBlank
    @Size(min = 5)
    private String messageBody;
}
