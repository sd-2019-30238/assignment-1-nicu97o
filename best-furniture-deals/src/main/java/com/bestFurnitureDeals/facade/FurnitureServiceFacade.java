package com.bestFurnitureDeals.facade;


import com.bestFurnitureDeals.dto.model.FurnitureDTO;

import java.util.List;

public interface FurnitureServiceFacade {
    FurnitureDTO getFurnitureById(long id);

    List<FurnitureDTO> getAllFurniture();

    void deleteFurniture(long id);

    void updateFurniture(FurnitureDTO furnitureDTO);

    void addFurniture(FurnitureDTO furnitureDTO);
}
