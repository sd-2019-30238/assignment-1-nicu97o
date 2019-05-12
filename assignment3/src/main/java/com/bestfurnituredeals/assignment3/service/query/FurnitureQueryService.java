package com.bestfurnituredeals.assignment3.service.query;

import com.bestfurnituredeals.assignment3.model.database.Furniture;

import java.util.List;

public interface FurnitureQueryService {
    Furniture getFurnitureById(long id);

    List<Furniture> getAllFurniture();
}
