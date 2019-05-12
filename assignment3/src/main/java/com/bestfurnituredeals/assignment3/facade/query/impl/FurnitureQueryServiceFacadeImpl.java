package com.bestfurnituredeals.assignment3.facade.query.impl;

import com.bestfurnituredeals.assignment3.facade.query.FurnitureQueryServiceFacade;
import com.bestfurnituredeals.assignment3.model.database.Furniture;
import com.bestfurnituredeals.assignment3.model.read.dto.FurnitureDTO;
import com.bestfurnituredeals.assignment3.model.read.mapper.Mapper;
import com.bestfurnituredeals.assignment3.service.query.FurnitureQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FurnitureQueryServiceFacadeImpl implements FurnitureQueryServiceFacade {
    private FurnitureQueryService furnitureQueryService;
    private Mapper<Furniture, FurnitureDTO> mapper;

    @Autowired
    public FurnitureQueryServiceFacadeImpl(FurnitureQueryService furnitureQueryService, Mapper<Furniture, FurnitureDTO> mapper) {
        this.furnitureQueryService = furnitureQueryService;
        this.mapper = mapper;
    }

    @Override
    public FurnitureDTO getFurnitureById(long id) {
        return mapper.convertToDTO(furnitureQueryService.getFurnitureById(id));
    }

    @Override
    public List<FurnitureDTO> getAllFurniture() {
        return furnitureQueryService.getAllFurniture().stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }
}
