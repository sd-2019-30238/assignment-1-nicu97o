package com.bestfurnituredeals.assignment3.controller.write;

import com.bestfurnituredeals.assignment3.facade.command.ClientOrderCommandServiceFacade;
import com.bestfurnituredeals.assignment3.facade.command.FeedbackMessageCommandServiceFacade;
import com.bestfurnituredeals.assignment3.model.write.FeedbackMessagePostCommandDTO;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrderCommandController {
    private ClientOrderCommandServiceFacade orderCommandServiceFacade;
    private FeedbackMessageCommandServiceFacade feedbackMessageCommandServiceFacade;

    public OrderCommandController(ClientOrderCommandServiceFacade orderCommandServiceFacade, FeedbackMessageCommandServiceFacade feedbackMessageCommandServiceFacade) {
        this.orderCommandServiceFacade = orderCommandServiceFacade;
        this.feedbackMessageCommandServiceFacade = feedbackMessageCommandServiceFacade;
    }

    @PostMapping("/feedbackMessage")
    public ModelAndView addFeedbackMessage(@Valid FeedbackMessagePostCommandDTO feedbackMessageDTO, @RequestParam("orderId") long orderId) {
        ModelAndView modelAndView = new ModelAndView("redirect:/orders/forUser");
        feedbackMessageCommandServiceFacade.postFeedbackMessageToAnOrder(orderId, feedbackMessageDTO);
        return modelAndView;
    }

    @PutMapping("/checkout")
    public ModelAndView checkout(Authentication authentication, @RequestParam(name = "subscribe", defaultValue = "false") boolean subscribe) {
        ModelAndView modelAndView = new ModelAndView("redirect:/orders/currentOrder");
        orderCommandServiceFacade.checkoutCurrentOrder(authentication.getName(), subscribe);
        return modelAndView;
    }

    @PutMapping("/approveOrder/{id}")
    public ModelAndView approveOrder(@PathVariable("id") long id) throws MessagingException {
        orderCommandServiceFacade.approveOrder(id);
        return new ModelAndView("redirect:/orders");
    }

    @PutMapping("/state/{id}")
    public ModelAndView updateState(@PathVariable("id") long id) throws MessagingException {
        orderCommandServiceFacade.updateOrderState(id);
        return new ModelAndView("redirect:/orders");
    }
}
