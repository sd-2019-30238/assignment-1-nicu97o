package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.ClientOrderDTO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;

import java.util.List;

public interface ClientOrderService {
    void addClientOrder(ClientOrderDTO clientOrderDTO);

    void updateClientOrder(ClientOrderDTO clientOrderDTO);

    void deleteClientOrder(ClientOrderDTO clientOrderDTO);

    List<ClientOrderDTO> getAllClientOrders();

    ClientOrderDTO getClientOrderById(long id);

    List<ClientOrderDTO> getAllFinishedOrdersForAnUser(String username);

    ClientOrderDTO getCurrentClientOrderForAnUser(String username);

    void approveClientOrder(ClientOrderDTO clientOrderDTO);
}
