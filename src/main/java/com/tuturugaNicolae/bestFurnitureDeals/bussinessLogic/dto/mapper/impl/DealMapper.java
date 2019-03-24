package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.DealDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.DealTypeDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.FurnitureDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.Mapper;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Deal;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.DealType;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Furniture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DealMapper implements Mapper<Deal, DealDTO> {
    private Mapper<Furniture, FurnitureDTO> furnitureMapper;

    @Autowired
    public DealMapper(Mapper<Furniture, FurnitureDTO> furnitureMapper) {
        this.furnitureMapper = furnitureMapper;
    }

    @Override
    public Deal convertToEntity(DealDTO dealDTO) {
        Deal deal = new Deal();
        deal.setId(dealDTO.getId());
        deal.setAvailable(dealDTO.isAvailable());
        deal.setAvailableQuantity(dealDTO.getAvailableQuantity());
        deal.setDealType(DealType.valueOf(dealDTO.getDealTypeDTO().toString()));
        deal.setName(dealDTO.getName());
        deal.setPrice(dealDTO.getPrice());
        deal.setFurniture(furnitureMapper.convertToEntity(dealDTO.getFurnitureDTO()));
        return deal;
    }

    @Override
    public DealDTO convertToDTO(Deal deal) {
        DealDTO dealDTO = new DealDTO();
        dealDTO.setId(deal.getId());
        dealDTO.setAvailable(deal.isAvailable());
        dealDTO.setAvailableQuantity(deal.getAvailableQuantity());
        dealDTO.setDealTypeDTO(DealTypeDTO.valueOf(deal.getDealType().toString()));
        dealDTO.setFurnitureDTO(furnitureMapper.convertToDTO(deal.getFurniture()));
        dealDTO.setName(deal.getName());
        dealDTO.setPrice(deal.getPrice());
        return dealDTO;
    }
}
