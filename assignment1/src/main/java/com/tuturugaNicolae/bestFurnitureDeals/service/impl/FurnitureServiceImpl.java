package com.tuturugaNicolae.bestFurnitureDeals.service.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.IsStaff;
import com.tuturugaNicolae.bestFurnitureDeals.service.FurnitureService;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.FurnitureDAO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Furniture;
import com.tuturugaNicolae.bestFurnitureDeals.exception.NoFurnitureFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FurnitureServiceImpl implements FurnitureService {
    private FurnitureDAO furnitureDAO;

    @Autowired
    public FurnitureServiceImpl(FurnitureDAO furnitureDAO) {
        this.furnitureDAO = furnitureDAO;
    }

    @Override
    public Furniture getFurnitureById(long id) {
        Furniture furniture = furnitureDAO.selectById(id);
        if (furniture == null) {
            throw new NoFurnitureFoundException("No furniture with id " + id + " found!");
        }
        return furniture;
    }

    @Override
    public List<Furniture> getAllFurniture() {
        List<Furniture> furniture = furnitureDAO.selectAll();
        if (furniture.isEmpty()) {
            throw new NoFurnitureFoundException();
        }
        return furniture;
    }

    @Override
    @IsStaff
    public void updateFurniture(Furniture furniture) {
        Furniture oldFurniture = getFurnitureById(furniture.getId());
        oldFurniture.setName(furniture.getName());
        oldFurniture.setDescription(furniture.getDescription());
        furnitureDAO.update(oldFurniture);
    }

    @Override
    @IsStaff
    public void deleteFurniture(Furniture furniture) {
        furnitureDAO.delete(getFurnitureById(furniture.getId()));
    }

    @Override
    @IsStaff
    public void addFurniture(Furniture furniture) {
        furniture.setId(0L);
        furnitureDAO.insert(furniture);
    }
}
