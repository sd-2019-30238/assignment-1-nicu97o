package com.bestfurnituredeals.assignment3.configuration;

import com.bestfurnituredeals.assignment3.handler.impl.*;
import com.bestfurnituredeals.assignment3.request.RequestType;
import com.bestfurnituredeals.assignment3.handler.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class HandlersContainer {
    private UserRequestHandler userCommandServiceFacade;
    private DealRequestHandler dealRequestHandler;
    private FurnitureRequestHandler furnitureRequestHandler;
    private ProductRequestHandler productRequestHandler;
    private FeedbackRequestHandler feedbackRequestHandler;
    private ClientOrderRequestHandler clientOrderRequestHandler;

    @Autowired
    public HandlersContainer(UserRequestHandler userCommandServiceFacade, DealRequestHandler dealRequestHandler, FurnitureRequestHandler furnitureRequestHandler, ProductRequestHandler productRequestHandler, FeedbackRequestHandler feedbackRequestHandler, ClientOrderRequestHandler clientOrderRequestHandler) {
        this.userCommandServiceFacade = userCommandServiceFacade;
        this.dealRequestHandler = dealRequestHandler;
        this.furnitureRequestHandler = furnitureRequestHandler;
        this.productRequestHandler = productRequestHandler;
        this.feedbackRequestHandler = feedbackRequestHandler;
        this.clientOrderRequestHandler = clientOrderRequestHandler;
    }

    @Bean
    public Map<RequestType, RequestHandler> getHandlers() {
        Map<RequestType, RequestHandler> handlerMap = new HashMap<>();
        handlerMap.put(RequestType.USER_COMMAND, userCommandServiceFacade);
        handlerMap.put(RequestType.DEAL_COMMAND, dealRequestHandler);
        handlerMap.put(RequestType.FURNITURE_COMMAND, furnitureRequestHandler);
        handlerMap.put(RequestType.PRODUCT_COMMAND, productRequestHandler);
        handlerMap.put(RequestType.FEEDBACK_COMMAND, feedbackRequestHandler);
        handlerMap.put(RequestType.ORDER_COMMAND, clientOrderRequestHandler);
        return handlerMap;
    }
}
