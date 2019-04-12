package com.bestFurnitureDeals.dto.mapper.impl;

import com.bestFurnitureDeals.dto.mapper.Mapper;
import com.bestFurnitureDeals.dto.model.ClientOrderDTO;
import com.bestFurnitureDeals.dto.model.OrderHistoryDTO;
import com.bestFurnitureDeals.dto.model.ProductDTO;
import com.bestFurnitureDeals.dto.model.UserDTO;
import com.bestFurnitureDeals.model.ClientOrder;
import com.bestFurnitureDeals.model.OrderHistory;
import com.bestFurnitureDeals.model.Product;
import com.bestFurnitureDeals.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ClientOrderMapper implements Mapper<ClientOrder, ClientOrderDTO> {
    private Mapper<User, UserDTO> userMapper;
    private Mapper<OrderHistory, OrderHistoryDTO> orderHistoryMapper;
    private Mapper<Product, ProductDTO> productMapper;

    @Autowired
    public ClientOrderMapper(Mapper<User, UserDTO> userMapper, Mapper<OrderHistory, OrderHistoryDTO> orderHistoryMapper, Mapper<Product, ProductDTO> productMapper) {
        this.userMapper = userMapper;
        this.orderHistoryMapper = orderHistoryMapper;
        this.productMapper = productMapper;
    }

    @Override
    public ClientOrder convertToEntity(ClientOrderDTO clientOrderDTO) {
        ClientOrder clientOrder = new ClientOrder();
        clientOrder.setId(clientOrderDTO.getId());
        clientOrder.setApproved(clientOrderDTO.isApproved());
        clientOrder.setPaymentMethod(clientOrderDTO.getPaymentMethod());
        clientOrder.setTotalPrice(clientOrderDTO.getTotalPrice());
        clientOrder.setFinished(clientOrderDTO.isFinished());
        if (clientOrderDTO.getUser() != null) {
            clientOrder.setUser(userMapper.convertToEntity(clientOrderDTO.getUser()));
        }
        if (clientOrderDTO.getOrderHistory() != null) {
            clientOrder.setOrderHistory(orderHistoryMapper.convertToEntity(clientOrderDTO.getOrderHistory()));
        }
        clientOrder.setProducts(new ArrayList<>());
        if (clientOrderDTO.getProducts() != null && !clientOrderDTO.getProducts().isEmpty()) {
            clientOrderDTO.getProducts().stream().map(productMapper::convertToEntity).forEach(clientOrder.getProducts()::add);
        }
        return clientOrder;
    }

    @Override
    public ClientOrderDTO convertToDTO(ClientOrder clientOrder) {
        ClientOrderDTO clientOrderDTO = new ClientOrderDTO();
        clientOrderDTO.setId(clientOrder.getId());
        clientOrderDTO.setApproved(clientOrder.isApproved());
        clientOrderDTO.setPaymentMethod(clientOrder.getPaymentMethod());
        clientOrderDTO.setTotalPrice(clientOrder.getTotalPrice());
        clientOrderDTO.setFinished(clientOrder.isFinished());
        if (clientOrder.getUser() != null) {
            clientOrderDTO.setUser(userMapper.convertToDTO(clientOrder.getUser()));
        }
        if (clientOrder.getOrderHistory() != null) {
            clientOrderDTO.setOrderHistory(orderHistoryMapper.convertToDTO(clientOrder.getOrderHistory()));
        }
        clientOrderDTO.setProducts(new ArrayList<>());
        if (clientOrder.getProducts() != null && !clientOrder.getProducts().isEmpty()) {
            clientOrder.getProducts().stream().map(productMapper::convertToDTO).forEach(clientOrderDTO.getProducts()::add);
        }
        return clientOrderDTO;
    }
}
