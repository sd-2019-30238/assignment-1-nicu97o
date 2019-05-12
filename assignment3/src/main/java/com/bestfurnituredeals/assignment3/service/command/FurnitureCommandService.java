package com.bestfurnituredeals.assignment3.service.command;

import com.bestfurnituredeals.assignment3.model.database.Furniture;

public interface FurnitureCommandService {
    void deleteFurniture(long id);

    void updateFurniture(Furniture furniture);

    void addFurniture(Furniture furniture);
}
