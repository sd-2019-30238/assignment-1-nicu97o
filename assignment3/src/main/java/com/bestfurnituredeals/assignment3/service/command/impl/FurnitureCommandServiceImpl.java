package com.bestfurnituredeals.assignment3.service.command.impl;

import com.bestfurnituredeals.assignment3.dao.FurnitureDAO;
import com.bestfurnituredeals.assignment3.model.database.Furniture;
import com.bestfurnituredeals.assignment3.service.command.FurnitureCommandService;
import com.bestfurnituredeals.assignment3.service.query.FurnitureQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FurnitureCommandServiceImpl implements FurnitureCommandService {
    private FurnitureDAO furnitureDAO;
    private FurnitureQueryService furnitureQueryService;

    @Autowired
    public FurnitureCommandServiceImpl(FurnitureDAO furnitureDAO, FurnitureQueryService furnitureQueryService) {
        this.furnitureDAO = furnitureDAO;
        this.furnitureQueryService = furnitureQueryService;
    }

    @Override
    @PreAuthorize("hasAuthority('STAFF')")
    public void deleteFurniture(long id) {
        Furniture furniture = furnitureQueryService.getFurnitureById(id);
        furnitureDAO.delete(furniture);
    }

    @Override
    @PreAuthorize("hasAuthority('STAFF')")
    public void updateFurniture(Furniture furniture) {
        Furniture oldFurniture = furnitureQueryService.getFurnitureById(furniture.getId());
        oldFurniture.setName(furniture.getName());
        oldFurniture.setDescription(furniture.getDescription());
        furnitureDAO.save(oldFurniture);
    }

    @Override
    @PreAuthorize("hasAuthority('STAFF')")
    public void addFurniture(Furniture furniture) {
        furnitureDAO.save(furniture);
    }
}
