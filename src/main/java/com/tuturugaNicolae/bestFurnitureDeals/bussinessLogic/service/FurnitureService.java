package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.FurnitureDTO;

import java.util.List;

public interface FurnitureService {
    FurnitureDTO getFurnitureById(long id);

    List<FurnitureDTO> getAllFurniture();

    void updateFurniture(FurnitureDTO furnitureDTO);

    void deleteFurniture(FurnitureDTO furnitureDTO);

    void addFurniture(FurnitureDTO furnitureDTO);
}
