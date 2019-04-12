package com.bestFurnitureDeals.service;

import com.bestFurnitureDeals.model.Furniture;

import java.util.List;

public interface FurnitureService {
    Furniture getFurnitureById(long id);

    List<Furniture> getAllFurniture();

    void deleteFurniture(long id);

    void updateFurniture(Furniture furniture);

    void addFurniture(Furniture furniture);
}
