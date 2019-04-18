package com.bestFurnitureDeals.controller;

import com.bestFurnitureDeals.dto.model.ProductDTO;
import com.bestFurnitureDeals.facade.DealServiceFacade;
import com.bestFurnitureDeals.facade.ProductServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ProductController {
    private DealServiceFacade dealServiceFacade;
    private ProductServiceFacade productServiceFacade;

    @Autowired
    public ProductController(DealServiceFacade dealServiceFacade, ProductServiceFacade productServiceFacade) {
        this.dealServiceFacade = dealServiceFacade;
        this.productServiceFacade = productServiceFacade;
    }

    @GetMapping("/productForm")
    public ModelAndView showAddProductForm(@RequestParam("dealId") long dealId) {
        ModelAndView modelAndView = new ModelAndView("addProductFormView");
        modelAndView.addObject("product", new ProductDTO());
        modelAndView.addObject("deal", dealServiceFacade.getDealById(dealId));
        return modelAndView;
    }

    @PostMapping
    public ModelAndView addProduct(@RequestParam("quantity") int quantity, @RequestParam("dealId") long dealId, Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("redirect:/deals");
        productServiceFacade.addProductToOrder(dealId, authentication.getName(), quantity);
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/orders/currentOrder");
        productServiceFacade.deleteProduct(id);
        return modelAndView;
    }
}
