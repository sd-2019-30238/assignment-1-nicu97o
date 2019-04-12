package com.bestFurnitureDeals.service.impl;

import com.bestFurnitureDeals.dao.FurnitureDAO;
import com.bestFurnitureDeals.exception.NoFurnitureFoundException;
import com.bestFurnitureDeals.model.Furniture;
import com.bestFurnitureDeals.service.FurnitureService;
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
        return furnitureDAO.findById(id).orElseThrow(() -> new NoFurnitureFoundException("No furniture with id " + id + " found!"));
    }

    @Override
    public List<Furniture> getAllFurniture() {
        List<Furniture> furniture = furnitureDAO.findAll();
//        if (furniture.isEmpty()) {
//            throw new NoFurnitureFoundException();
//        }
        return furniture;
    }

    @Override
    public void deleteFurniture(long id) {
        Furniture furniture = getFurnitureById(id);
        furnitureDAO.delete(furniture);
    }

    @Override
    public void updateFurniture(Furniture furniture) {
        Furniture oldFurniture = getFurnitureById(furniture.getId());
        oldFurniture.setName(furniture.getName());
        oldFurniture.setDescription(furniture.getDescription());
        furnitureDAO.save(oldFurniture);
    }

    @Override
    public void addFurniture(Furniture furniture) {
        furnitureDAO.save(furniture);
    }
}
