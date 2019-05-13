package com.bestfurnituredeals.assignment3.controller.write;

import com.bestfurnituredeals.assignment3.mediator.Mediator;
import com.bestfurnituredeals.assignment3.model.write.ProductAddCommandDTO;
import com.bestfurnituredeals.assignment3.request.Request;
import com.bestfurnituredeals.assignment3.request.RequestType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ProductCommandController {
    private Mediator mediator;

    @Autowired
    public ProductCommandController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public ModelAndView addProduct(@RequestParam("quantity") int quantity, @RequestParam("dealId") long dealId, Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("redirect:/deals");
        mediator.handle(new Request("postProduct", new ProductAddCommandDTO(dealId, quantity, authentication.getName()), RequestType.PRODUCT_COMMAND));
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/orders/currentOrder");
        mediator.handle(new Request("deleteProduct", id, RequestType.PRODUCT_COMMAND));
        return modelAndView;
    }
}
