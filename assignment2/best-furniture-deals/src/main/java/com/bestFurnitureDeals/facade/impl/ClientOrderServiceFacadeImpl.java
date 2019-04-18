package com.bestFurnitureDeals.facade.impl;

import com.bestFurnitureDeals.dto.mapper.Mapper;
import com.bestFurnitureDeals.dto.model.ClientOrderDTO;
import com.bestFurnitureDeals.facade.ClientOrderServiceFacade;
import com.bestFurnitureDeals.model.ClientOrder;
import com.bestFurnitureDeals.service.ClientOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientOrderServiceFacadeImpl implements ClientOrderServiceFacade {
    private ClientOrderService clientOrderService;
    private Mapper<ClientOrder, ClientOrderDTO> mapper;

    @Autowired
    public ClientOrderServiceFacadeImpl(ClientOrderService clientOrderService, Mapper<ClientOrder, ClientOrderDTO> mapper) {
        this.clientOrderService = clientOrderService;
        this.mapper = mapper;
    }

    @Override
    public ClientOrderDTO getClientOrderById(long id) {
        return mapper.convertToDTO(clientOrderService.getClientOrderById(id));
    }

    @Override
    public ClientOrderDTO addClientOrder(ClientOrderDTO clientOrderDTO, String loggedUser) {
        return mapper.convertToDTO(clientOrderService.addClientOrder(mapper.convertToEntity(clientOrderDTO), loggedUser));
    }

    @Override
    public ClientOrderDTO getCurrentClientOrderForAnUsr(String loggedUser) {
        return mapper.convertToDTO(clientOrderService.getCurrentClientOrderForAnUser(loggedUser));
    }

    @Override
    public List<ClientOrderDTO> getOrdersForAnUser(String loggedUser) {
        return clientOrderService.getOrdersForAnUser(loggedUser).stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void updateOrderState(long id) throws MessagingException {
        clientOrderService.updateOrderState(id);
    }

    @Override
    public void checkoutCurrentOrder(String username, boolean subscribe) {
        clientOrderService.checkoutCurrentOrder(username, subscribe);
    }

    @Override
    public void approveOrder(long orderId) throws MessagingException {
        clientOrderService.approveOrder(orderId);
    }

    @Override
    public List<ClientOrderDTO> getAllOrders() {
        return clientOrderService.getAllOrders().stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ClientOrderDTO> getAllUnapprovedClientOrders() {
        return clientOrderService.getAllUnapprovedClientOrders().stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }
}
