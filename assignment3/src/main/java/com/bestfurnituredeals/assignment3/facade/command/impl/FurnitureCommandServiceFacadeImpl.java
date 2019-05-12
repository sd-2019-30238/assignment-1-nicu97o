package com.bestfurnituredeals.assignment3.facade.command.impl;

import com.bestfurnituredeals.assignment3.facade.command.FurnitureCommandServiceFacade;
import com.bestfurnituredeals.assignment3.model.database.Furniture;
import com.bestfurnituredeals.assignment3.model.write.FurnitureAddCommandDTO;
import com.bestfurnituredeals.assignment3.model.write.FurnitureUpdateCommandDTO;
import com.bestfurnituredeals.assignment3.service.command.FurnitureCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FurnitureCommandServiceFacadeImpl implements FurnitureCommandServiceFacade {
    private FurnitureCommandService furnitureCommandService;

    @Autowired
    public FurnitureCommandServiceFacadeImpl(FurnitureCommandService furnitureCommandService) {
        this.furnitureCommandService = furnitureCommandService;
    }

    @Override
    public void deleteFurniture(long id) {
        furnitureCommandService.deleteFurniture(id);
    }

    @Override
    public void updateFurniture(FurnitureUpdateCommandDTO furnitureUpdateCommandDTO) {
        Furniture furniture = new Furniture();
        furniture.setId(furnitureUpdateCommandDTO.getId());
        furniture.setName(furnitureUpdateCommandDTO.getName());
        furniture.setDescription(furnitureUpdateCommandDTO.getDescription());
        furnitureCommandService.updateFurniture(furniture);
    }

    @Override
    public void addFurniture(FurnitureAddCommandDTO furnitureAddCommandDTO) {
        Furniture furniture = new Furniture();
        furniture.setName(furnitureAddCommandDTO.getName());
        furniture.setDescription(furnitureAddCommandDTO.getDescription());
        furnitureCommandService.addFurniture(furniture);
    }
}
