package com.bestfurnituredeals.assignment3.model.read.mapper.impl;

import com.bestfurnituredeals.assignment3.model.database.Deal;
import com.bestfurnituredeals.assignment3.model.database.Furniture;
import com.bestfurnituredeals.assignment3.model.read.mapper.Mapper;
import com.bestfurnituredeals.assignment3.model.read.dto.DealDTO;
import com.bestfurnituredeals.assignment3.model.read.dto.FurnitureDTO;
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
        deal.setDealType(dealDTO.getDealType());
        deal.setName(dealDTO.getName());
        deal.setPrice(dealDTO.getPrice());
        if (dealDTO.getFurniture() != null) {
            deal.setFurniture(furnitureMapper.convertToEntity(dealDTO.getFurniture()));
        }
        return deal;
    }

    @Override
    public DealDTO convertToDTO(Deal deal) {
        DealDTO dealDTO = new DealDTO();
        dealDTO.setId(deal.getId());
        dealDTO.setAvailable(deal.isAvailable());
        dealDTO.setAvailableQuantity(deal.getAvailableQuantity());
        dealDTO.setDealType(deal.getDealType());
        if (deal.getFurniture() != null) {
            dealDTO.setFurniture(furnitureMapper.convertToDTO(deal.getFurniture()));
        }
        dealDTO.setName(deal.getName());
        dealDTO.setPrice(deal.getPrice());
        return dealDTO;
    }
}
