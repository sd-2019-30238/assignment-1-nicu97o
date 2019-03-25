package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.Mapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.ClientOrderDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.IsStaff;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.SecurityContext;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.ClientOrderService;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.UserService;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.Validator;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.ClientOrderDAO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.OrderHistoryDAO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.*;
import com.tuturugaNicolae.bestFurnitureDeals.exception.ForbiddenException;
import com.tuturugaNicolae.bestFurnitureDeals.exception.InvalidClientOrderException;
import com.tuturugaNicolae.bestFurnitureDeals.exception.NoClientOrderFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientOrderServiceImpl implements ClientOrderService {
    private SecurityContext securityContext;
    private Validator<ClientOrderDTO> validator;
    private Mapper<ClientOrder, ClientOrderDTO> clientOrderMapper;
    private UserService userService;
    private ClientOrderDAO clientOrderDAO;
    private OrderHistoryDAO orderHistoryDAO;

    @Autowired
    public ClientOrderServiceImpl(SecurityContext securityContext, Validator<ClientOrderDTO> validator, Mapper<ClientOrder, ClientOrderDTO> clientOrderMapper, UserService userService, ClientOrderDAO clientOrderDAO, OrderHistoryDAO orderHistoryDAO) {
        this.securityContext = securityContext;
        this.validator = validator;
        this.clientOrderMapper = clientOrderMapper;
        this.userService = userService;
        this.clientOrderDAO = clientOrderDAO;
        this.orderHistoryDAO = orderHistoryDAO;
    }

    @Override
    public void addClientOrder(ClientOrderDTO clientOrderDTO) {
        if (!validator.validate(clientOrderDTO)) {
            throw new InvalidClientOrderException();
        }
        User user = userService.getUserEntityByUsername(securityContext.getLoggedUser().get().getUsername());
        ClientOrder clientOrder = clientOrderMapper.convertToEntity(clientOrderDTO);
        clientOrder.setClient(user);
        clientOrderDAO.insert(clientOrder);
        OrderHistory orderHistory = new OrderHistory(0L, clientOrder, LocalDateTime.now(), OrderState.PIKING);
        orderHistoryDAO.insert(orderHistory);
    }

    @Override
    public void updateClientOrder(ClientOrderDTO clientOrderDTO) {
        if (!validator.validate(clientOrderDTO)) {
            throw new InvalidClientOrderException();
        }
        if (!clientOrderDTO.getClient().getUsername().equals(securityContext.getLoggedUser().get().getUsername())) {
            throw new ForbiddenException();
        }
        ClientOrder clientOrder = getClientOrderEntityById(clientOrderDTO.getId());
        clientOrder.setTotalPrice(clientOrderDTO.getTotalPrice());
        clientOrder.setFinished(clientOrderDTO.isFinished());
        clientOrderDAO.update(clientOrder);
    }

    @Override
    public void deleteClientOrder(ClientOrderDTO clientOrderDTO) {
        ClientOrder clientOrder = getClientOrderEntityById(clientOrderDTO.getId());
        clientOrderDAO.delete(clientOrder);
    }

    @Override
    @IsStaff
    public List<ClientOrderDTO> getAllClientOrders() {
        List<ClientOrder> clientOrders = clientOrderDAO.selectAll();
        if (clientOrders.isEmpty()) {
            throw new NoClientOrderFoundException();
        }
        return clientOrders.stream().map(clientOrderMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public ClientOrderDTO getClientOrderById(long id) {
        return clientOrderMapper.convertToDTO(getClientOrderEntityById(id));
    }

    @Override
    public List<ClientOrderDTO> getAllFinishedOrdersForAnUser(String username) {
        List<ClientOrder> clientOrders = clientOrderDAO.findAllFinishedOrdersForAnUser(username);
        if (clientOrders.isEmpty()) {
            throw new NoClientOrderFoundException();
        }
        return clientOrders.stream().map(clientOrderMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public ClientOrderDTO getCurrentClientOrderForAnUser(String username) {
        Optional<ClientOrder> clientOrder = clientOrderDAO.findClientOrderByUser(username);
        if (!clientOrder.isPresent()) {
            ClientOrder newClientOrder = new ClientOrder(0L, false, PaymentMethod.CASH, BigDecimal.ZERO, false);
            ClientOrderDTO newClientOrderDTO = clientOrderMapper.convertToDTO(newClientOrder);
            addClientOrder(newClientOrderDTO);
            return newClientOrderDTO;
        }
        return clientOrderMapper.convertToDTO(clientOrder.get());
    }

    @Override
    @IsStaff
    public void approveClientOrder(ClientOrderDTO clientOrderDTO) {
        ClientOrder clientOrder = getClientOrderEntityById(clientOrderDTO.getId());
        clientOrder.setApproved(true);
        clientOrderDAO.update(clientOrder);
    }

    private ClientOrder getClientOrderEntityById(long id) {
        ClientOrder clientOrder = clientOrderDAO.selectById(id);
        if (clientOrder == null) {
            throw new NoClientOrderFoundException();
        }
        return clientOrder;
    }
}
