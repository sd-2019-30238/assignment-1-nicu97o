package com.bestfurnituredeals.assignment3.facade.query;

import com.bestfurnituredeals.assignment3.model.read.dto.FurnitureDTO;

import java.util.List;

public interface FurnitureQueryServiceFacade {
    FurnitureDTO getFurnitureById(long id);

    List<FurnitureDTO> getAllFurniture();
}
