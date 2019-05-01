package com.bestfurnituredeals.assignment3.model.read.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FurnitureDTO {
    private Long id;

    private String name;

    private String description;
}
