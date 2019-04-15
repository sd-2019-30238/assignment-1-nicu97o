package com.bestFurnitureDeals.facade;

import com.bestFurnitureDeals.dto.model.ClientOrderDTO;

import javax.mail.MessagingException;
import java.util.List;

public interface ClientOrderServiceFacade {
    ClientOrderDTO getClientOrderById(long id);

    ClientOrderDTO addClientOrder(ClientOrderDTO clientOrderDTO, String loggedUser);

    ClientOrderDTO getCurrentClientOrderForAnUsr(String loggedUser);

    List<ClientOrderDTO> getOrdersForAnUser(String loggedUser);

    void updateOrderState(long id) throws MessagingException;

    void checkoutCurrentOrder(String username, boolean subscribe);

    void approveOrder(long orderId) throws MessagingException;

    List<ClientOrderDTO> getAllOrders();

    List<ClientOrderDTO> getAllUnapprovedClientOrders();
}
