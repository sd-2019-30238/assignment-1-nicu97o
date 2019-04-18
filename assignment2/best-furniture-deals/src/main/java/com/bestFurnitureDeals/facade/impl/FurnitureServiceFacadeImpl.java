package com.bestFurnitureDeals.facade.impl;

import com.bestFurnitureDeals.dto.mapper.Mapper;
import com.bestFurnitureDeals.dto.model.FurnitureDTO;
import com.bestFurnitureDeals.facade.FurnitureServiceFacade;
import com.bestFurnitureDeals.model.Furniture;
import com.bestFurnitureDeals.service.FurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FurnitureServiceFacadeImpl implements FurnitureServiceFacade {
    private FurnitureService furnitureService;
    private Mapper<Furniture, FurnitureDTO> mapper;

    @Autowired
    public FurnitureServiceFacadeImpl(FurnitureService furnitureService, Mapper<Furniture, FurnitureDTO> mapper) {
        this.furnitureService = furnitureService;
        this.mapper = mapper;
    }

    @Override
    public FurnitureDTO getFurnitureById(long id) {
        return mapper.convertToDTO(furnitureService.getFurnitureById(id));
    }

    @Override
    public List<FurnitureDTO> getAllFurniture() {
        return furnitureService.getAllFurniture().stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteFurniture(long id) {
        furnitureService.deleteFurniture(id);
    }

    @Override
    public void updateFurniture(FurnitureDTO furnitureDTO) {
        furnitureService.updateFurniture(mapper.convertToEntity(furnitureDTO));
    }

    @Override
    public void addFurniture(FurnitureDTO furnitureDTO) {
        furnitureService.addFurniture(mapper.convertToEntity(furnitureDTO));
    }
}
