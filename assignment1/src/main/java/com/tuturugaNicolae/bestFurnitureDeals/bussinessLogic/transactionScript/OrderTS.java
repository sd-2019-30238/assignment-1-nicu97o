package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.transactionScript;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.mapper.Mapper;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.ClientOrderDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.FeedbackMessageDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.OrderHistoryDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.SecurityContext;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.validator.Validator;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.ClientOrder;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.FeedbackMessage;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.OrderHistory;
import com.tuturugaNicolae.bestFurnitureDeals.exception.CantPostFeedbackMessageException;
import com.tuturugaNicolae.bestFurnitureDeals.service.ClientOrderService;
import com.tuturugaNicolae.bestFurnitureDeals.service.FeedbackMessageService;
import com.tuturugaNicolae.bestFurnitureDeals.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderTS {
    private SecurityContext securityContext;
    private ClientOrderService clientOrderService;
    private OrderHistoryService orderHistoryService;
    private Mapper<ClientOrder, ClientOrderDTO> clientOrderMapper;
    private Mapper<OrderHistory, OrderHistoryDTO> orderHistoryMapper;
    private Mapper<FeedbackMessage, FeedbackMessageDTO> feedbackMessageMapper;
    private Validator<FeedbackMessageDTO> feedbackMessageValidator;
    private FeedbackMessageService feedbackMessageService;

    @Autowired
    public OrderTS(SecurityContext securityContext, ClientOrderService clientOrderService, OrderHistoryService orderHistoryService, Mapper<ClientOrder, ClientOrderDTO> clientOrderMapper, Mapper<OrderHistory, OrderHistoryDTO> orderHistoryMapper, Mapper<FeedbackMessage, FeedbackMessageDTO> feedbackMessageMapper, Validator<FeedbackMessageDTO> feedbackMessageValidator, FeedbackMessageService feedbackMessageService) {
        this.securityContext = securityContext;
        this.clientOrderService = clientOrderService;
        this.orderHistoryService = orderHistoryService;
        this.clientOrderMapper = clientOrderMapper;
        this.orderHistoryMapper = orderHistoryMapper;
        this.feedbackMessageMapper = feedbackMessageMapper;
        this.feedbackMessageValidator = feedbackMessageValidator;
        this.feedbackMessageService = feedbackMessageService;
    }

    public List<ClientOrderDTO> getFinishedOrdersForLoggedUser() {
        List<ClientOrder> clientOrders = clientOrderService.getAllFinishedOrdersForAnUser(securityContext.getNameOfAuthenticatedUser());
        return clientOrders.stream().map(clientOrderMapper::convertToDTO).collect(Collectors.toList());
    }

    public OrderHistoryDTO getOrderHistoryForAClientOrder(ClientOrderDTO clientOrderDTO) {
        return orderHistoryMapper.convertToDTO(orderHistoryService.getOrderHistoryBasedOnClientOrder(clientOrderMapper.convertToEntity(clientOrderDTO)));
    }

    public void postFeedbackMessage(FeedbackMessageDTO feedbackMessageDTO, OrderHistoryDTO orderHistoryDTO) {
        if (!feedbackMessageValidator.validate(feedbackMessageDTO)) {
            throw new CantPostFeedbackMessageException("Invalid feedback message!");
        }
        FeedbackMessage feedbackMessage = feedbackMessageMapper.convertToEntity(feedbackMessageDTO);
        OrderHistory orderHistory = orderHistoryMapper.convertToEntity(orderHistoryDTO);
        feedbackMessageService.addFeedbackMessageToAnOrder(feedbackMessage, orderHistory);
    }

    public List<ClientOrderDTO> getAllOrders() {
        return clientOrderService.getAllClientOrders().stream().map(clientOrderMapper::convertToDTO).collect(Collectors.toList());
    }

    public List<ClientOrderDTO> getAllFinishedOrders() {
        return clientOrderService.getAllFinishedClientOrders().stream().map(clientOrderMapper::convertToDTO).collect(Collectors.toList());
    }

    public void approveClientOrder(ClientOrderDTO clientOrderDTO) {
        ClientOrder clientOrder = clientOrderService.getClientOrderById(clientOrderDTO.getId());
        clientOrderService.approveClientOrder(clientOrder);
        orderHistoryService.updateOrderHistoryState(orderHistoryService.getOrderHistoryBasedOnClientOrder(clientOrder));
    }

    public void updateOrderState(ClientOrderDTO clientOrderDTO) {
        orderHistoryService.updateOrderHistoryState(orderHistoryService.getOrderHistoryBasedOnClientOrder(clientOrderMapper.convertToEntity(clientOrderDTO)));
    }

    public FeedbackMessageDTO getFeedbackMessageByClientOrder(ClientOrderDTO clientOrderDTO) {
        return feedbackMessageMapper.convertToDTO(feedbackMessageService.getFeedbackMessageByClientOrderId(clientOrderDTO.getId()));
    }

    public ClientOrderDTO getCurrentClientOrder() {
        return clientOrderMapper.convertToDTO(clientOrderService.getCurrentClientOrderForAnUser(securityContext.getLoggedUser().get()));
    }
}
