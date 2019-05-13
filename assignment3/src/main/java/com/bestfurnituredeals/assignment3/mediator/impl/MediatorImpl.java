package com.bestfurnituredeals.assignment3.mediator.impl;

import com.bestfurnituredeals.assignment3.request.Request;
import com.bestfurnituredeals.assignment3.request.RequestType;
import com.bestfurnituredeals.assignment3.handler.RequestHandler;
import com.bestfurnituredeals.assignment3.mediator.Mediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MediatorImpl implements Mediator {
    private Map<RequestType, RequestHandler> handlerMap;

    @Autowired
    public MediatorImpl(Map<RequestType, RequestHandler> handlerMap) {
        this.handlerMap = handlerMap;
    }

    @Override
    public void handle(Request request) {
        handlerMap.get(request.getRequestType()).handleRequest(request);
    }
}
