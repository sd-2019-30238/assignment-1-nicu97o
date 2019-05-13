package com.bestfurnituredeals.assignment3.controller.write;

import com.bestfurnituredeals.assignment3.mediator.Mediator;
import com.bestfurnituredeals.assignment3.model.write.CheckoutCommandDTO;
import com.bestfurnituredeals.assignment3.model.write.FeedbackMessagePostCommandDTO;
import com.bestfurnituredeals.assignment3.request.Request;
import com.bestfurnituredeals.assignment3.request.RequestType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrderCommandController {
    private Mediator mediator;

    @Autowired
    public OrderCommandController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/feedbackMessage")
    public ModelAndView addFeedbackMessage(@Valid FeedbackMessagePostCommandDTO feedbackMessageDTO, @RequestParam("orderId") long orderId) {
        ModelAndView modelAndView = new ModelAndView("redirect:/orders/forUser");
        feedbackMessageDTO.setOrderId(orderId);
        mediator.handle(new Request("addFeedbackMessage", feedbackMessageDTO, RequestType.FEEDBACK_COMMAND));
        return modelAndView;
    }

    @PutMapping("/checkout")
    public ModelAndView checkout(Authentication authentication, @RequestParam(name = "subscribe", defaultValue = "false") boolean subscribe) {
        ModelAndView modelAndView = new ModelAndView("redirect:/orders/currentOrder");
        mediator.handle(new Request("checkout", new CheckoutCommandDTO(authentication.getName(), subscribe), RequestType.ORDER_COMMAND));
        return modelAndView;
    }

    @PutMapping("/approveOrder/{id}")
    public ModelAndView approveOrder(@PathVariable("id") long id) {
        mediator.handle(new Request("approveOrder", id, RequestType.ORDER_COMMAND));
        return new ModelAndView("redirect:/orders");
    }

    @PutMapping("/state/{id}")
    public ModelAndView updateState(@PathVariable("id") long id) {
        mediator.handle(new Request("updateState", id, RequestType.ORDER_COMMAND));
        return new ModelAndView("redirect:/orders");
    }
}
