package com.bestfurnituredeals.assignment3.controller.read;

import com.bestfurnituredeals.assignment3.facade.query.DealQueryServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ProductQueryController {
    private DealQueryServiceFacade dealQueryServiceFacade;

    @Autowired
    public ProductQueryController(DealQueryServiceFacade dealQueryServiceFacade) {
        this.dealQueryServiceFacade = dealQueryServiceFacade;
    }

    @GetMapping("/productForm")
    public ModelAndView showAddProductForm(@RequestParam("dealId") long dealId) {
        ModelAndView modelAndView = new ModelAndView("addProductFormView");
        //modelAndView.addObject("product", new ProductDTO());
        modelAndView.addObject("deal", dealQueryServiceFacade.getDealById(dealId));
        return modelAndView;
    }

}
