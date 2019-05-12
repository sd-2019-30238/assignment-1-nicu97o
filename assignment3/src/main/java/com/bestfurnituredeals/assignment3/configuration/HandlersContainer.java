package com.bestfurnituredeals.assignment3.configuration;

import com.bestfurnituredeals.assignment3.facade.command.UserCommandServiceFacade;
import com.bestfurnituredeals.assignment3.facade.query.UserQueryServiceFacade;
import com.bestfurnituredeals.assignment3.request.RequestType;
import com.bestfurnituredeals.assignment3.handler.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class HandlersContainer {
    private UserCommandServiceFacade userCommandServiceFacade;

    @Autowired
    public HandlersContainer(UserCommandServiceFacade userCommandServiceFacade) {
        this.userCommandServiceFacade = userCommandServiceFacade;
    }

    @Bean
    public Map<RequestType, RequestHandler> getHandlers() {
        Map<RequestType, RequestHandler> handlerMap = new HashMap<>();
        handlerMap.put(RequestType.USER_COMMAND, userCommandServiceFacade);
        return handlerMap;
    }
}
