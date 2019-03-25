package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.controller;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.Mapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.BoughtFurnitureDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.DealDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.factory.DealComputerFactory;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.SecurityContext;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.BoughtFurnitureService;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.ClientOrderService;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.DealService;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.OrderHistoryService;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.BoughtFurniture;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Deal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartController {
    private Mapper<Deal, DealDTO> dealMapper;
    private SecurityContext securityContext;
    private OrderHistoryService orderHistoryService;
    private ClientOrderService clientOrderService;
    private DealService dealService;
    private BoughtFurnitureService boughtFurnitureService;
    private Mapper<BoughtFurniture, BoughtFurnitureDTO> boughtFurnitureMapper;

    @Autowired
    public CartController(Mapper<Deal, DealDTO> dealMapper, SecurityContext securityContext, OrderHistoryService orderHistoryService, ClientOrderService clientOrderService, DealService dealService, BoughtFurnitureService boughtFurnitureService, Mapper<BoughtFurniture, BoughtFurnitureDTO> boughtFurnitureMapper) {
        this.dealMapper = dealMapper;
        this.securityContext = securityContext;
        this.orderHistoryService = orderHistoryService;
        this.clientOrderService = clientOrderService;
        this.dealService = dealService;
        this.boughtFurnitureService = boughtFurnitureService;
        this.boughtFurnitureMapper = boughtFurnitureMapper;
    }

    public void addProductToCart(DealDTO dealDTO, int quantity) {
        if (quantity <= 0) {
            ///
        }
        ClientOrder clientOrder = clientOrderService.getCurrentClientOrderForAnUser(securityContext.getLoggedUser().get());
        Deal deal = dealService.getDealById(dealDTO.getId());
        BoughtFurniture boughtFurniture = DealComputerFactory.create(dealDTO.getDealTypeDTO()).computeFurnitureOrderDetails(clientOrder, deal, quantity);
        boughtFurnitureService.addNewFurnitureToCurrentClientOrder(boughtFurniture, clientOrder);
    }

    public void deleteProductFromCart(BoughtFurnitureDTO boughtFurnitureDTO) {
        boughtFurnitureService.deleteBoughtFurniture(boughtFurnitureMapper.convertToEntity(boughtFurnitureDTO));
    }

    public List<BoughtFurnitureDTO> getAllProductsFromCart() {
        ClientOrder currentClientOrder = clientOrderService.getCurrentClientOrderForAnUser(securityContext.getLoggedUser().get());
        return boughtFurnitureService.getAllBoughtFurnitureForCurrentClientOrder(currentClientOrder).stream().map(boughtFurnitureMapper::convertToDTO).collect(Collectors.toList());
    }
}
//    private SecurityContext securityContext;
//    private Validator<ClientOrderDTO> validator;
//    private Mapper<ClientOrder, ClientOrderDTO> clientOrderMapper;
//    private ClientOrderDAO clientOrderDAO;
//    private OrderHistoryService orderHistoryService;
//
//    @Autowired
//    public ClientOrderServiceImpl(SecurityContext securityContext, Validator<ClientOrderDTO> validator, Mapper<ClientOrder, ClientOrderDTO> clientOrderMapper, ClientOrderDAO clientOrderDAO, OrderHistoryService orderHistoryService) {
//        this.securityContext = securityContext;
//        this.validator = validator;
//        this.clientOrderMapper = clientOrderMapper;
//        this.clientOrderDAO = clientOrderDAO;
//        this.orderHistoryService = orderHistoryService;
//    }
//
//    @Override
//    public ClientOrderDTO addClientOrder(ClientOrderDTO clientOrderDTO) {
//        if (!validator.validate(clientOrderDTO)) {
//            throw new InvalidClientOrderException();
//        }
//        ClientOrder clientOrder = clientOrderMapper.convertToEntity(clientOrderDTO);
//        clientOrderDAO.insert(clientOrder);
//        OrderHistory orderHistory = new OrderHistory(0L, clientOrder, LocalDateTime.now(), OrderState.PIKING);
//        orderHistoryService.addNewOrderHistory(orderHistory);
//        return clientOrderMapper.convertToDTO(clientOrder);
//    }
//
//    @Override
//    public void updateClientOrder(ClientOrderDTO clientOrderDTO) {
//        if (!validator.validate(clientOrderDTO)) {
//            throw new InvalidClientOrderException();
//        }
//        if (!clientOrderDTO.getClient().getUsername().equals(securityContext.getLoggedUser().get().getUsername())) {
//            throw new ForbiddenException();
//        }
//        ClientOrder clientOrder = getClientOrderEntityById(clientOrderDTO.getId());
//        clientOrder.setTotalPrice(clientOrderDTO.getTotalPrice());
//        clientOrder.setFinished(clientOrderDTO.isFinished());
//        clientOrderDAO.update(clientOrder);
//    }
//
//    @Override
//    public void deleteClientOrder(ClientOrderDTO clientOrderDTO) {
//        ClientOrder clientOrder = getClientOrderEntityById(clientOrderDTO.getId());
//        clientOrderDAO.delete(clientOrder);
//    }
//
//    @Override
//    @IsStaff
//    public List<ClientOrderDTO> getAllClientOrders() {
//        List<ClientOrder> clientOrders = clientOrderDAO.selectAll();
//        if (clientOrders.isEmpty()) {
//            throw new NoClientOrderFoundException();
//        }
//        return clientOrders.stream().map(clientOrderMapper::convertToDTO).collect(Collectors.toList());
//    }
//
//    @Override
//    public ClientOrderDTO getClientOrderById(long id) {
//        return clientOrderMapper.convertToDTO(getClientOrderEntityById(id));
//    }
//
//    @Override
//    public List<ClientOrderDTO> getAllFinishedOrdersForAnUser(String username) {
//        List<ClientOrder> clientOrders = clientOrderDAO.findAllFinishedOrdersForAnUser(username);
//        if (clientOrders.isEmpty()) {
//            throw new NoClientOrderFoundException();
//        }
//        return clientOrders.stream().map(clientOrderMapper::convertToDTO).collect(Collectors.toList());
//    }
//
//    @Override
//    public ClientOrderDTO getCurrentClientOrderForAnUser(String username) {
//        Optional<ClientOrder> clientOrder = clientOrderDAO.findClientOrderByUser(username);
//        if (!clientOrder.isPresent()) {
//            ClientOrder newClientOrder = new ClientOrder(0L, false, PaymentMethod.CASH, BigDecimal.ZERO, false);
//            ClientOrderDTO newClientOrderDTO = clientOrderMapper.convertToDTO(newClientOrder);
//            newClientOrderDTO.setClient(securityContext.getLoggedUser().get());
//            return addClientOrder(newClientOrderDTO);
//        }
//        return clientOrderMapper.convertToDTO(clientOrder.get());
//    }
//
//    @Override
//    @IsStaff
//    public void approveClientOrder(ClientOrderDTO clientOrderDTO) {
//        ClientOrder clientOrder = getClientOrderEntityById(clientOrderDTO.getId());
//        clientOrder.setApproved(true);
//        clientOrderDAO.update(clientOrder);
//    }
//
//    private ClientOrder getClientOrderEntityById(long id) {
//        ClientOrder clientOrder = clientOrderDAO.selectById(id);
//        if (clientOrder == null) {
//            throw new NoClientOrderFoundException();
//        }
//        return clientOrder;
//    }

//    private ClientOrderService clientOrderService;
//    private SecurityContext securityContext;
//    private Mapper<BoughtFurniture, BoughtFurnitureDTO> mapper;
//    private BoughtFurnitureDAO boughtFurnitureDAO;
//    private DealService dealService;
//
//    @Autowired
//    public CartServiceImpl(ClientOrderService clientOrderService, SecurityContext securityContext, Mapper<BoughtFurniture, BoughtFurnitureDTO> mapper, BoughtFurnitureDAO boughtFurnitureDAO, DealService dealService) {
//        this.clientOrderService = clientOrderService;
//        this.securityContext = securityContext;
//        this.mapper = mapper;
//        this.boughtFurnitureDAO = boughtFurnitureDAO;
//        this.dealService = dealService;
//    }
//

//
//    @Override
//    public void deleteProductFromCart(BoughtFurnitureDTO boughtFurnitureDTO) {
//        BoughtFurniture boughtFurniture = boughtFurnitureDAO.selectById(boughtFurnitureDTO.getId());
//        boughtFurnitureDAO.delete(boughtFurniture);
//    }
//
//    @Override
//    public List<BoughtFurnitureDTO> getAllProductsFromCart() {
//        ClientOrderDTO clientOrderDTO = clientOrderService.getCurrentClientOrderForAnUser(securityContext.getLoggedUser().get().getUsername());
//        return boughtFurnitureDAO.findCurrentProductsForCurrentClientOrder(clientOrderDTO.getId()).stream().map(mapper::convertToDTO).collect(Collectors.toList());
//    }