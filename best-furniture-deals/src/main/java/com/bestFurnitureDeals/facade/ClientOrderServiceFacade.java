package com.bestFurnitureDeals.facade;

import com.bestFurnitureDeals.dto.model.ClientOrderDTO;

import java.util.List;

public interface ClientOrderServiceFacade {
    ClientOrderDTO getClientOrderById(long id);

    ClientOrderDTO addClientOrder(ClientOrderDTO clientOrderDTO, String loggedUser);

    ClientOrderDTO getCurrentClientOrderForAnUsr(String loggedUser);

    List<ClientOrderDTO> getOrdersForAnUser(String loggedUser);

    void updateOrderState(long id);

    void checkoutCurrentOrder(String username);

    void approveOrder(long orderId);

    List<ClientOrderDTO> getAllOrders();

    List<ClientOrderDTO> getAllUnapprovedClientOrders();
}
