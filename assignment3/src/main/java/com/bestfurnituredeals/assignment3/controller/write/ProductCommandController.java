package com.bestfurnituredeals.assignment3.controller.write;

import com.bestfurnituredeals.assignment3.facade.command.ProductCommandServiceFacade;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ProductCommandController {
    private ProductCommandServiceFacade productCommandServiceFacade;

    public ProductCommandController(ProductCommandServiceFacade productCommandServiceFacade) {
        this.productCommandServiceFacade = productCommandServiceFacade;
    }

    @PostMapping
    public ModelAndView addProduct(@RequestParam("quantity") int quantity, @RequestParam("dealId") long dealId, Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("redirect:/deals");
        productCommandServiceFacade.addProductToOrder(dealId, authentication.getName(), quantity);
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/orders/currentOrder");
        productCommandServiceFacade.deleteProduct(id);
        return modelAndView;
    }
}
