package com.bestfurnituredeals.assignment3.controller.read;

import com.bestfurnituredeals.assignment3.facade.query.ClientOrderQueryServiceFacade;
import com.bestfurnituredeals.assignment3.facade.query.FeedbackMessageQueryServiceFacade;
import com.bestfurnituredeals.assignment3.model.write.FeedbackMessagePostCommandDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/orders")
public class OrderQueryController {
    private ClientOrderQueryServiceFacade clientOrderQueryServiceFacade;
    private FeedbackMessageQueryServiceFacade feedbackMessageQueryServiceFacade;

    @Autowired
    public OrderQueryController(ClientOrderQueryServiceFacade clientOrderQueryServiceFacade, FeedbackMessageQueryServiceFacade feedbackMessageQueryServiceFacade) {
        this.clientOrderQueryServiceFacade = clientOrderQueryServiceFacade;
        this.feedbackMessageQueryServiceFacade = feedbackMessageQueryServiceFacade;
    }

    @GetMapping("/forUser")
    public ModelAndView getAllOrdersForAnUser(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("userOrdersView");
        modelAndView.addObject("userOrders", clientOrderQueryServiceFacade.getOrdersForAnUser(authentication.getName()));
        return modelAndView;
    }

    @GetMapping("/currentOrder")
    public ModelAndView getCurrentClientOrderForLoggedUser(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("orderView");
        modelAndView.addObject("order", clientOrderQueryServiceFacade.getCurrentClientOrderForAnUser(authentication.getName()));
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getSelectedOrder(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("orderView");
        modelAndView.addObject("order", clientOrderQueryServiceFacade.getClientOrderById(id));
        return modelAndView;
    }

    @GetMapping("/feedbackMessageForm")
    public ModelAndView showFeedbackMessageForm(@RequestParam("orderId") long orderId) {
        ModelAndView modelAndView = new ModelAndView("feedbackForm");
        modelAndView.addObject("feedbackMessageDTO", new FeedbackMessagePostCommandDTO());
        modelAndView.addObject("orderId", orderId);
        return modelAndView;
    }

    @GetMapping("/feedbackMessage")
    public ModelAndView getFeedbackMessage(@RequestParam("orderId") long orderId) {
        ModelAndView modelAndView = new ModelAndView("feedbackMessageView");
        modelAndView.addObject("feedbackMessage", feedbackMessageQueryServiceFacade.getFeedbackMessageByOrderId(orderId));
        return modelAndView;
    }

    @GetMapping
    public ModelAndView getAllClientOrders() {
        ModelAndView modelAndView = new ModelAndView("userOrdersView");
        modelAndView.addObject("userOrders", clientOrderQueryServiceFacade.getAllOrders());
        return modelAndView;
    }

    @GetMapping("/unapproved")
    public ModelAndView getAllUnapprovedClientOrders() {
        ModelAndView modelAndView = new ModelAndView("userOrdersView");
        modelAndView.addObject("userOrders", clientOrderQueryServiceFacade.getAllUnapprovedClientOrders());
        return modelAndView;
    }
}
