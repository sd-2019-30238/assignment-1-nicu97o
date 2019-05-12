package com.bestfurnituredeals.assignment3.service.query.impl;

import com.bestfurnituredeals.assignment3.dao.FurnitureDAO;
import com.bestfurnituredeals.assignment3.exception.NoFurnitureFoundException;
import com.bestfurnituredeals.assignment3.model.database.Furniture;
import com.bestfurnituredeals.assignment3.service.query.FurnitureQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FurnitureQueryServiceImpl implements FurnitureQueryService {
    private FurnitureDAO furnitureDAO;

    @Autowired
    public FurnitureQueryServiceImpl(FurnitureDAO furnitureDAO) {
        this.furnitureDAO = furnitureDAO;
    }

    @Override
    public Furniture getFurnitureById(long id) {
        return furnitureDAO.findById(id).orElseThrow(() -> new NoFurnitureFoundException("No furniture with id " + id + " found!"));
    }

    @Override
    public List<Furniture> getAllFurniture() {
        List<Furniture> furniture = furnitureDAO.findAll();
        return furniture;
    }
}
