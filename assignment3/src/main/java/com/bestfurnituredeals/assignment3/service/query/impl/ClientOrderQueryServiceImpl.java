package com.bestfurnituredeals.assignment3.service.query.impl;

import com.bestfurnituredeals.assignment3.dao.ClientOrderDAO;
import com.bestfurnituredeals.assignment3.exception.NoClientOrderFoundException;
import com.bestfurnituredeals.assignment3.model.database.ClientOrder;
import com.bestfurnituredeals.assignment3.model.database.PaymentMethod;
import com.bestfurnituredeals.assignment3.service.command.ClientOrderCommandService;
import com.bestfurnituredeals.assignment3.service.query.ClientOrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientOrderQueryServiceImpl implements ClientOrderQueryService {
    private ClientOrderDAO clientOrderDAO;
    private ClientOrderCommandService clientOrderCommandService;

    @Autowired
    public ClientOrderQueryServiceImpl(ClientOrderDAO clientOrderDAO, ClientOrderCommandService clientOrderCommandService) {
        this.clientOrderDAO = clientOrderDAO;
        this.clientOrderCommandService = clientOrderCommandService;
    }

    @Override
    public ClientOrder getClientOrderById(long id) {
        Optional<ClientOrder> clientOrder = clientOrderDAO.findById(id);
        if (!clientOrder.isPresent()) {
            throw new NoClientOrderFoundException("No client order found for user with id " + id + " found!");
        }
        return clientOrder.get();
    }

    @Override
    public ClientOrder getCurrentClientOrderForAnUser(String loggedUser) {
        Optional<ClientOrder> clientOrder = clientOrderDAO.findClientOrderByUserUsernameAndAndFinishedFalse(loggedUser);
        if (clientOrder.isPresent()) {
            return clientOrder.get();
        }
        return clientOrderCommandService.addClientOrder(new ClientOrder(false, PaymentMethod.CASH, BigDecimal.ZERO, false), loggedUser);
    }

    @Override
    @PreAuthorize("hasAuthority('STAFF')")
    public List<ClientOrder> getAllOrders() {
        return clientOrderDAO.findAll();
    }

    @Override
    @PreAuthorize("hasAuthority('STAFF')")
    public List<ClientOrder> getAllUnapprovedClientOrders() {
        return clientOrderDAO.findClientOrdersByApprovedFalseAndFinishedTrue();
    }

    @Override
    public List<ClientOrder> getOrdersForAnUser(String loggedUser) {
        return clientOrderDAO.findClientOrdersByUserUsername(loggedUser);
    }
}
