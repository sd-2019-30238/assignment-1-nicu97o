package com.bestfurnituredeals.assignment3.model.read.mapper.impl;

import com.bestfurnituredeals.assignment3.model.database.Furniture;
import com.bestfurnituredeals.assignment3.model.read.mapper.Mapper;
import com.bestfurnituredeals.assignment3.model.read.dto.FurnitureDTO;
import org.springframework.stereotype.Component;

@Component
public class FurnitureMapper implements Mapper<Furniture, FurnitureDTO> {
    @Override
    public Furniture convertToEntity(FurnitureDTO furnitureDTO) {
        Furniture furniture = new Furniture();
        furniture.setId(furnitureDTO.getId());
        furniture.setName(furnitureDTO.getName());
        furniture.setDescription(furnitureDTO.getDescription());
        return furniture;
    }

    @Override
    public FurnitureDTO convertToDTO(Furniture furniture) {
        FurnitureDTO furnitureDTO = new FurnitureDTO();
        furnitureDTO.setId(furniture.getId());
        furnitureDTO.setName(furniture.getName());
        furnitureDTO.setDescription(furniture.getDescription());
        return furnitureDTO;
    }
}
