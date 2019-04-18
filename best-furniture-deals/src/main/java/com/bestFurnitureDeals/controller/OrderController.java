package com.bestFurnitureDeals.controller;

import com.bestFurnitureDeals.dto.model.FeedbackMessageDTO;
import com.bestFurnitureDeals.facade.ClientOrderServiceFacade;
import com.bestFurnitureDeals.facade.FeedbackMessageServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private ClientOrderServiceFacade clientOrderServiceFacade;
    private FeedbackMessageServiceFacade feedbackMessageServiceFacade;

    @Autowired
    public OrderController(ClientOrderServiceFacade clientOrderServiceFacade, FeedbackMessageServiceFacade feedbackMessageServiceFacade) {
        this.clientOrderServiceFacade = clientOrderServiceFacade;
        this.feedbackMessageServiceFacade = feedbackMessageServiceFacade;
    }

    @GetMapping("/forUser")
    public ModelAndView getAllOrdersForAnUser(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("userOrdersView");
        modelAndView.addObject("userOrders", clientOrderServiceFacade.getOrdersForAnUser(authentication.getName()));
        return modelAndView;
    }

    @GetMapping("/currentOrder")
    public ModelAndView getCurrentClientOrderForLoggedUser(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("orderView");
        modelAndView.addObject("order", clientOrderServiceFacade.getCurrentClientOrderForAnUsr(authentication.getName()));
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getSelectedOrder(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("orderView");
        modelAndView.addObject("order", clientOrderServiceFacade.getClientOrderById(id));
        return modelAndView;
    }

    @GetMapping("/feedbackMessageForm")
    public ModelAndView showFeedbackMessageForm(@RequestParam("orderId") long orderId) {
        ModelAndView modelAndView = new ModelAndView("feedbackForm");
        modelAndView.addObject("feedbackMessageDTO", new FeedbackMessageDTO());
        modelAndView.addObject("orderId", orderId);
        return modelAndView;
    }

    @PostMapping("/feedbackMessage")
    public ModelAndView addFeedbackMessage(@Valid FeedbackMessageDTO feedbackMessageDTO, @RequestParam("orderId") long orderId) {
        ModelAndView modelAndView = new ModelAndView("redirect:/orders/forUser");
        feedbackMessageServiceFacade.postFeedbackMessageToAnOrder(orderId, feedbackMessageDTO);
        return modelAndView;
    }

    @GetMapping("/feedbackMessage")
    public ModelAndView getFeedbackMessage(@RequestParam("orderId") long orderId) {
        ModelAndView modelAndView = new ModelAndView("feedbackMessageView");
        modelAndView.addObject("feedbackMessage", feedbackMessageServiceFacade.getFeedbackMessageByOrderId(orderId));
        return modelAndView;
    }

    @PutMapping("/checkout")
    public ModelAndView checkout(Authentication authentication, @RequestParam(name = "subscribe", defaultValue = "false") boolean subscribe) {
        ModelAndView modelAndView = new ModelAndView("redirect:/orders/currentOrder");
        clientOrderServiceFacade.checkoutCurrentOrder(authentication.getName(), subscribe);
        return modelAndView;
    }

    @PutMapping("/approveOrder/{id}")
    public ModelAndView approveOrder(@PathVariable("id") long id) throws MessagingException {
        clientOrderServiceFacade.approveOrder(id);
        return new ModelAndView("redirect:/orders");
    }

    @PutMapping("/state/{id}")
    public ModelAndView updateState(@PathVariable("id") long id) throws MessagingException {
        clientOrderServiceFacade.updateOrderState(id);
        return new ModelAndView("redirect:/orders");
    }

    @GetMapping
    public ModelAndView getAllClientOrders() {
        ModelAndView modelAndView = new ModelAndView("userOrdersView");
        modelAndView.addObject("userOrders", clientOrderServiceFacade.getAllOrders());
        return modelAndView;
    }

    @GetMapping("/unapproved")
    public ModelAndView getAllUnapprovedClientOrders() {
        ModelAndView modelAndView = new ModelAndView("userOrdersView");
        modelAndView.addObject("userOrders", clientOrderServiceFacade.getAllUnapprovedClientOrders());
        return modelAndView;
    }
}
