package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.controller;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.Mapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.FurnitureDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.FurnitureService;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.Validator;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Furniture;
import com.tuturugaNicolae.bestFurnitureDeals.exception.InvalidFurnitureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FurnitureController {
    private Validator<Furniture> furnitureValidator;
    private FurnitureService furnitureService;
    private Mapper<Furniture, FurnitureDTO> mapper;

    @Autowired
    public FurnitureController(Validator<Furniture> furnitureValidator, FurnitureService furnitureService, Mapper<Furniture, FurnitureDTO> mapper) {
        this.furnitureValidator = furnitureValidator;
        this.furnitureService = furnitureService;
        this.mapper = mapper;
    }

    public FurnitureDTO getFurnitureById(long id) {
        return mapper.convertToDTO(furnitureService.getFurnitureById(id));
    }

    public List<FurnitureDTO> getAllFurniture() {
        return furnitureService.getAllFurniture().stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    public void updateFurniture(FurnitureDTO furnitureDTO) {
        Furniture furniture = mapper.convertToEntity(furnitureDTO);
        if (!furnitureValidator.validate(furniture)) {
            throw new InvalidFurnitureException();
        }
        furnitureService.updateFurniture(furniture);
    }

    public void deleteFurniture(FurnitureDTO furnitureDTO) {
        furnitureService.deleteFurniture(mapper.convertToEntity(furnitureDTO));
    }

    public void addFurniture(FurnitureDTO furnitureDTO) {
        Furniture furniture = mapper.convertToEntity(furnitureDTO);
        if (!furnitureValidator.validate(furniture)) {
            throw new InvalidFurnitureException();
        }
        furnitureService.addFurniture(mapper.convertToEntity(furnitureDTO));
    }
}