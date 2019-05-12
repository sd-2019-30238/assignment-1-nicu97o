package com.bestfurnituredeals.assignment3.facade.command;

import com.bestfurnituredeals.assignment3.model.database.Furniture;
import com.bestfurnituredeals.assignment3.model.write.FurnitureAddCommandDTO;
import com.bestfurnituredeals.assignment3.model.write.FurnitureUpdateCommandDTO;

public interface FurnitureCommandServiceFacade {
    void deleteFurniture(long id);

    void updateFurniture(FurnitureUpdateCommandDTO furnitureUpdateCommandDTO);

    void addFurniture(FurnitureAddCommandDTO furnitureAddCommandDTO);
}
