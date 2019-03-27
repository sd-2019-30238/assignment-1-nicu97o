package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.controller;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.Mapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.BoughtFurnitureDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.DealDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.factory.DealComputerFactory;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.SecurityContext;
import com.tuturugaNicolae.bestFurnitureDeals.service.BoughtFurnitureService;
import com.tuturugaNicolae.bestFurnitureDeals.service.ClientOrderService;
import com.tuturugaNicolae.bestFurnitureDeals.service.DealService;
import com.tuturugaNicolae.bestFurnitureDeals.service.OrderHistoryService;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.BoughtFurniture;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Deal;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.OrderHistory;
import com.tuturugaNicolae.bestFurnitureDeals.exception.UnavailableDealException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartController {
    private SecurityContext securityContext;
    private OrderHistoryService orderHistoryService;
    private ClientOrderService clientOrderService;
    private DealService dealService;
    private BoughtFurnitureService boughtFurnitureService;
    private Mapper<BoughtFurniture, BoughtFurnitureDTO> boughtFurnitureMapper;

    @Autowired
    public CartController(SecurityContext securityContext, OrderHistoryService orderHistoryService, ClientOrderService clientOrderService, DealService dealService, BoughtFurnitureService boughtFurnitureService, Mapper<BoughtFurniture, BoughtFurnitureDTO> boughtFurnitureMapper) {
        this.securityContext = securityContext;
        this.orderHistoryService = orderHistoryService;
        this.clientOrderService = clientOrderService;
        this.dealService = dealService;
        this.boughtFurnitureService = boughtFurnitureService;
        this.boughtFurnitureMapper = boughtFurnitureMapper;
    }

    public void addProductToCart(DealDTO dealDTO, int quantity) {
        if (!dealDTO.isAvailable()) {
            throw new UnavailableDealException();
        }
        if (dealDTO.getAvailableQuantity() < quantity) {
            throw new UnavailableDealException("Not enough products on stock");
        }
        ClientOrder clientOrder = clientOrderService.getCurrentClientOrderForAnUser(securityContext.getLoggedUser().get());
        Deal deal = dealService.getDealById(dealDTO.getId());
        BoughtFurniture boughtFurniture = DealComputerFactory.create(dealDTO.getDealTypeDTO()).computeFurnitureOrderDetails(clientOrder, deal, quantity);
        boughtFurnitureService.addNewFurnitureToCurrentClientOrder(boughtFurniture, clientOrder);
        clientOrderService.updateClientOrder(clientOrder, securityContext.getLoggedUser().get());
        if (deal.getAvailableQuantity() == 0) {
            deal.setAvailable(false);
        }
        dealService.updateDealQuantityAndAvailability(deal);
    }

    public void deleteProductFromCart(BoughtFurnitureDTO boughtFurnitureDTO) {
        ClientOrder clientOrder = clientOrderService.getCurrentClientOrderForAnUser(securityContext.getLoggedUser().get());
        boughtFurnitureService.deleteBoughtFurniture(boughtFurnitureMapper.convertToEntity(boughtFurnitureDTO));
        clientOrder.setTotalPrice(clientOrder.getTotalPrice().subtract(boughtFurnitureDTO.getPrice()));
        clientOrderService.updateClientOrder(clientOrder, securityContext.getLoggedUser().get());
    }

    public List<BoughtFurnitureDTO> getAllProductsFromCart() {
        ClientOrder currentClientOrder = clientOrderService.getCurrentClientOrderForAnUser(securityContext.getLoggedUser().get());
        return boughtFurnitureService.getAllBoughtFurnitureForCurrentClientOrder(currentClientOrder).stream().map(boughtFurnitureMapper::convertToDTO).collect(Collectors.toList());
    }

    public void checkout() {
        ClientOrder currentClientOrder = clientOrderService.getCurrentClientOrderForAnUser(securityContext.getLoggedUser().get());
        currentClientOrder.setFinished(true);
        clientOrderService.updateClientOrder(currentClientOrder, securityContext.getLoggedUser().get());
        OrderHistory orderHistory = orderHistoryService.getOrderHistoryBasedOnClientOrder(currentClientOrder);
        orderHistoryService.placeOrder(orderHistory);
    }
}