package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.Mapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.FurnitureDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.IsStaff;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.FurnitureService;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.Validator;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.FurnitureDAO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Furniture;
import com.tuturugaNicolae.bestFurnitureDeals.exception.InvalidFurnitureException;
import com.tuturugaNicolae.bestFurnitureDeals.exception.NoFurnitureFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FurnitureServiceImpl implements FurnitureService {
    private FurnitureDAO furnitureDAO;
    private Mapper<Furniture, FurnitureDTO> furnitureMapper;
    private Validator<FurnitureDTO> furnitureValidator;

    @Autowired
    public FurnitureServiceImpl(FurnitureDAO furnitureDAO, Mapper<Furniture, FurnitureDTO> furnitureMapper, Validator<FurnitureDTO> furnitureValidator) {
        this.furnitureDAO = furnitureDAO;
        this.furnitureMapper = furnitureMapper;
        this.furnitureValidator = furnitureValidator;
    }

    @Override
    public FurnitureDTO getFurnitureById(long id) {
        return furnitureMapper.convertToDTO(getFurnitureEntityById(id));
    }

    @Override
    public List<FurnitureDTO> getAllFurniture() {
        List<Furniture> furniture = furnitureDAO.selectAll();
        if (furniture.isEmpty()) {
            throw new NoFurnitureFoundException();
        }
        return furniture.stream().map(furnitureMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    @IsStaff
    public void updateFurniture(FurnitureDTO furnitureDTO) {
        if (!furnitureValidator.validate(furnitureDTO)) {
            throw new InvalidFurnitureException();
        }
        Furniture furniture = getFurnitureEntityById(furnitureDTO.getId());
        furniture.setName(furnitureDTO.getName());
        furniture.setDescription(furnitureDTO.getDescription());
        furnitureDAO.update(furniture);
    }

    @Override
    @IsStaff
    public void deleteFurniture(FurnitureDTO furnitureDTO) {
        Furniture furniture = getFurnitureEntityById(furnitureDTO.getId());
        furnitureDAO.delete(furniture);
    }

    @Override
    @IsStaff
    public void addFurniture(FurnitureDTO furnitureDTO) {
        if (!furnitureValidator.validate(furnitureDTO)) {
            throw new InvalidFurnitureException();
        }
        furnitureDTO.setId(0L);
        furnitureDAO.insert(furnitureMapper.convertToEntity(furnitureDTO));
    }

    private Furniture getFurnitureEntityById(long id) {
        Furniture furniture = furnitureDAO.selectById(id);
        if (furniture == null) {
            throw new NoFurnitureFoundException("No furniture with id " + id + " found!");
        }
        return furniture;
    }
}
