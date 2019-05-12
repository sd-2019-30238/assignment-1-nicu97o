package com.bestfurnituredeals.assignment3.facade.query.impl;

import com.bestfurnituredeals.assignment3.facade.query.ClientOrderQueryServiceFacade;
import com.bestfurnituredeals.assignment3.model.database.ClientOrder;
import com.bestfurnituredeals.assignment3.model.read.dto.ClientOrderDTO;
import com.bestfurnituredeals.assignment3.model.read.mapper.Mapper;
import com.bestfurnituredeals.assignment3.service.query.ClientOrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientOrderQueryServiceFacadeImpl implements ClientOrderQueryServiceFacade {
    private ClientOrderQueryService clientOrderQueryService;
    private Mapper<ClientOrder, ClientOrderDTO> mapper;

    @Autowired
    public ClientOrderQueryServiceFacadeImpl(ClientOrderQueryService clientOrderQueryService, Mapper<ClientOrder, ClientOrderDTO> mapper) {
        this.clientOrderQueryService = clientOrderQueryService;
        this.mapper = mapper;
    }

    @Override
    public ClientOrderDTO getClientOrderById(long id) {
        return mapper.convertToDTO(clientOrderQueryService.getClientOrderById(id));
    }

    @Override
    public ClientOrderDTO getCurrentClientOrderForAnUser(String loggedUser) {
        return mapper.convertToDTO(clientOrderQueryService.getCurrentClientOrderForAnUser(loggedUser));
    }

    @Override
    public List<ClientOrderDTO> getAllOrders() {
        return clientOrderQueryService.getAllOrders().stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ClientOrderDTO> getAllUnapprovedClientOrders() {
        return clientOrderQueryService.getAllUnapprovedClientOrders().stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ClientOrderDTO> getOrdersForAnUser(String loggedUser) {
        return clientOrderQueryService.getOrdersForAnUser(loggedUser).stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }
}
