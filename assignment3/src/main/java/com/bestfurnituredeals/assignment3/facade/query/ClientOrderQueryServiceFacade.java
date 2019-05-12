package com.bestfurnituredeals.assignment3.facade.query;

import com.bestfurnituredeals.assignment3.model.read.dto.ClientOrderDTO;

import java.util.List;

public interface ClientOrderQueryServiceFacade {
    ClientOrderDTO getClientOrderById(long id);

    ClientOrderDTO getCurrentClientOrderForAnUser(String loggedUser);

    List<ClientOrderDTO> getAllOrders();

    List<ClientOrderDTO> getAllUnapprovedClientOrders();

    List<ClientOrderDTO> getOrdersForAnUser(String loggedUser);
}
