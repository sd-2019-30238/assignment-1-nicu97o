package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.BoughtFurnitureDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.ClientOrderDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.FurnitureDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.Mapper;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.BoughtFurniture;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Furniture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BoughtFurnitureMapper implements Mapper<BoughtFurniture, BoughtFurnitureDTO> {
    private Mapper<ClientOrder, ClientOrderDTO> clientOrderMapper;
    private Mapper<Furniture, FurnitureDTO> furnitureMapper;

    @Autowired
    public BoughtFurnitureMapper(Mapper<ClientOrder, ClientOrderDTO> clientOrderMapper, Mapper<Furniture, FurnitureDTO> furnitureMapper) {
        this.clientOrderMapper = clientOrderMapper;
        this.furnitureMapper = furnitureMapper;
    }

    @Override
    public BoughtFurniture convertToEntity(BoughtFurnitureDTO boughtFurnitureDTO) {
        BoughtFurniture boughtFurniture = new BoughtFurniture();
        boughtFurniture.setId(boughtFurnitureDTO.getId());
        boughtFurniture.setBoughtQuantity(boughtFurnitureDTO.getBoughtQuantity());
        boughtFurniture.setClientOrder(clientOrderMapper.convertToEntity(boughtFurnitureDTO.getClientOrderDTO()));
        boughtFurniture.setFurniture(furnitureMapper.convertToEntity(boughtFurnitureDTO.getFurnitureDTO()));
        boughtFurniture.setPrice(boughtFurnitureDTO.getPrice());
        return boughtFurniture;
    }

    @Override
    public BoughtFurnitureDTO convertToDTO(BoughtFurniture boughtFurniture) {
        BoughtFurnitureDTO boughtFurnitureDTO = new BoughtFurnitureDTO();
        boughtFurnitureDTO.setId(boughtFurniture.getId());
        boughtFurnitureDTO.setBoughtQuantity(boughtFurniture.getBoughtQuantity());
        if (boughtFurniture.getClientOrder() == null) {
            boughtFurnitureDTO.setClientOrderDTO(null);
        } else {
            boughtFurnitureDTO.setClientOrderDTO(clientOrderMapper.convertToDTO(boughtFurniture.getClientOrder()));
        }
        if (boughtFurniture.getFurniture() == null) {
            boughtFurnitureDTO.setFurnitureDTO(null);
        } else {
            boughtFurnitureDTO.setFurnitureDTO(furnitureMapper.convertToDTO(boughtFurniture.getFurniture()));
        }
        boughtFurnitureDTO.setPrice(boughtFurniture.getPrice());
        return boughtFurnitureDTO;
    }
}
