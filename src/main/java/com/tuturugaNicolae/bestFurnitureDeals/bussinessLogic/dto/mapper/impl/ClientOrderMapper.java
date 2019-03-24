package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.ClientOrderDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.UserDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.Mapper;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientOrderMapper implements Mapper<ClientOrder, ClientOrderDTO> {
    private Mapper<User, UserDTO> userMapper;

    @Autowired
    public ClientOrderMapper(Mapper<User, UserDTO> userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public ClientOrder convertToEntity(ClientOrderDTO clientOrderDTO) {
        ClientOrder clientOrder = new ClientOrder();
        clientOrder.setId(clientOrderDTO.getId());
        clientOrder.setApproved(clientOrderDTO.isApproved());
        clientOrder.setFinished(clientOrderDTO.isFinished());
        clientOrder.setPaymentMethod(clientOrderDTO.getPaymentMethod());
        clientOrder.setTotalPrice(clientOrderDTO.getTotalPrice());
        clientOrder.setClient(userMapper.convertToEntity(clientOrderDTO.getClient()));
        return clientOrder;
    }

    @Override
    public ClientOrderDTO convertToDTO(ClientOrder clientOrder) {
        ClientOrderDTO clientOrderDTO = new ClientOrderDTO();
        clientOrderDTO.setId(clientOrder.getId());
        clientOrderDTO.setApproved(clientOrder.isApproved());
        clientOrderDTO.setFinished(clientOrder.isFinished());
        clientOrderDTO.setPaymentMethod(clientOrder.getPaymentMethod());
        clientOrderDTO.setTotalPrice(clientOrder.getTotalPrice());
        clientOrderDTO.setClient(userMapper.convertToDTO(clientOrder.getClient()));
        return clientOrderDTO;
    }
}
