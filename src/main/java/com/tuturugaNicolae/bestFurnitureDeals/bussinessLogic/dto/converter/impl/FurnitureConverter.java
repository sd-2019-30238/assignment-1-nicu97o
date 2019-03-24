package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.converter.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.converter.Converter;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.FurnitureDTO;
import org.springframework.stereotype.Component;

@Component
public class FurnitureConverter implements Converter<FurnitureDTO> {
    @Override
    public FurnitureDTO convertToDTO(String object) {
        FurnitureDTO furnitureDTO = new FurnitureDTO();
        String[] fieldSplitted = object.split(", ");
        furnitureDTO.setId(Long.parseLong(fieldSplitted[0].split("=")[1]));
        furnitureDTO.setName(fieldSplitted[1].split("=")[1]);
        furnitureDTO.setDescription(fieldSplitted[2].split("=")[1]);
        return furnitureDTO;
    }
}
