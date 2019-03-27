package com.tuturugaNicolae.bestFurnitureDeals.service;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Furniture;

import java.util.List;

public interface FurnitureService {
    Furniture getFurnitureById(long id);

    List<Furniture> getAllFurniture();

    void updateFurniture(Furniture furniture);

    void deleteFurniture(Furniture furniture);

    void addFurniture(Furniture furniture);
}
