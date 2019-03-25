package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.impl;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.Mapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.BoughtFurnitureDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.ClientOrderDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.DealDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.factory.DealComputerFactory;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.SecurityContext;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.CartService;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.ClientOrderService;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.DealComputer;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.DealService;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.BoughtFurnitureDAO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.BoughtFurniture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartServiceImpl implements CartService {
    private ClientOrderService clientOrderService;
    private SecurityContext securityContext;
    private Mapper<BoughtFurniture, BoughtFurnitureDTO> mapper;
    private BoughtFurnitureDAO boughtFurnitureDAO;
    private DealService dealService;

    @Autowired
    public CartServiceImpl(ClientOrderService clientOrderService, SecurityContext securityContext, Mapper<BoughtFurniture, BoughtFurnitureDTO> mapper, BoughtFurnitureDAO boughtFurnitureDAO, DealService dealService) {
        this.clientOrderService = clientOrderService;
        this.securityContext = securityContext;
        this.mapper = mapper;
        this.boughtFurnitureDAO = boughtFurnitureDAO;
        this.dealService = dealService;
    }

    @Override
    public void addProductToCart(DealDTO dealDTO, int quantity) {
        DealComputer dealComputer = DealComputerFactory.create(dealDTO.getDealTypeDTO());
        ClientOrderDTO clientOrderDTO = clientOrderService.getCurrentClientOrderForAnUser(securityContext.getLoggedUser().get().getUsername());
        BoughtFurnitureDTO boughtFurnitureDTO = dealComputer.computeFurnitureOrderDetails(clientOrderDTO, dealDTO, quantity);
        boughtFurnitureDAO.insert(mapper.convertToEntity(boughtFurnitureDTO));
    }

    @Override
    public void deleteProductFromCart(BoughtFurnitureDTO boughtFurnitureDTO) {
        BoughtFurniture boughtFurniture = boughtFurnitureDAO.selectById(boughtFurnitureDTO.getId());
        boughtFurnitureDAO.delete(boughtFurniture);
    }

    @Override
    public List<BoughtFurnitureDTO> getAllProductsFromCart() {
        ClientOrderDTO clientOrderDTO = clientOrderService.getCurrentClientOrderForAnUser(securityContext.getLoggedUser().get().getUsername());
        return boughtFurnitureDAO.findCurrentProductsForCurrentClientOrder(clientOrderDTO.getId()).stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }
}
