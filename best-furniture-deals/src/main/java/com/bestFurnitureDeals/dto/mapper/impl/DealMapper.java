package com.bestFurnitureDeals.dto.mapper.impl;

import com.bestFurnitureDeals.dto.mapper.Mapper;
import com.bestFurnitureDeals.dto.model.DealDTO;
import com.bestFurnitureDeals.dto.model.FurnitureDTO;
import com.bestFurnitureDeals.model.Deal;
import com.bestFurnitureDeals.model.Furniture;
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
