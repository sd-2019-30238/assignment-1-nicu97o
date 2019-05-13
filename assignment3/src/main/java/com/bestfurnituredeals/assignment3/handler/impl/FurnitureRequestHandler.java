package com.bestfurnituredeals.assignment3.handler.impl;

import com.bestfurnituredeals.assignment3.handler.RequestHandler;
import com.bestfurnituredeals.assignment3.model.database.Furniture;
import com.bestfurnituredeals.assignment3.model.write.FurnitureAddCommandDTO;
import com.bestfurnituredeals.assignment3.model.write.FurnitureUpdateCommandDTO;
import com.bestfurnituredeals.assignment3.request.Request;
import com.bestfurnituredeals.assignment3.service.command.FurnitureCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FurnitureRequestHandler implements RequestHandler {
    private FurnitureCommandService furnitureCommandService;

    @Autowired
    public FurnitureRequestHandler(FurnitureCommandService furnitureCommandService) {
        this.furnitureCommandService = furnitureCommandService;
    }

    @Override
    public void handleRequest(Request request) {
        if (request.getRequestName().equals("deleteFurniture")) {
            deleteFurniture(request);
        } else if (request.getRequestName().equals("addFurniture")) {
            addFurniture(request);
        } else if (request.getRequestName().equals("updateFurniture")) {
            updateFurniture(request);
        }
    }

    private void deleteFurniture(Request request) {
        furnitureCommandService.deleteFurniture((Long) request.getRequestObject());
    }

    private void updateFurniture(Request request) {
        Furniture furniture = new Furniture();
        furniture.setId(((FurnitureUpdateCommandDTO) request.getRequestObject()).getId());
        furniture.setName(((FurnitureUpdateCommandDTO) request.getRequestObject()).getName());
        furniture.setDescription(((FurnitureUpdateCommandDTO) request.getRequestObject()).getDescription());
        furnitureCommandService.updateFurniture(furniture);
    }

    private void addFurniture(Request request) {
        Furniture furniture = new Furniture();
        furniture.setName(((FurnitureAddCommandDTO) request.getRequestObject()).getName());
        furniture.setDescription(((FurnitureAddCommandDTO) request.getRequestObject()).getDescription());
        furnitureCommandService.addFurniture(furniture);
    }
}
