package com.bestfurnituredeals.assignment3.controller.read;

import com.bestfurnituredeals.assignment3.facade.query.DealQueryServiceFacade;
import com.bestfurnituredeals.assignment3.facade.query.FurnitureQueryServiceFacade;
import com.bestfurnituredeals.assignment3.model.database.DealType;
import com.bestfurnituredeals.assignment3.model.write.DealAddCommandDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
@RequestMapping("/deals")
public class DealQueryController {
    private DealQueryServiceFacade dealQueryServiceFacade;
    private FurnitureQueryServiceFacade furnitureQueryServiceFacade;

    @Autowired
    public DealQueryController(DealQueryServiceFacade dealQueryServiceFacade, FurnitureQueryServiceFacade furnitureQueryServiceFacade) {
        this.dealQueryServiceFacade = dealQueryServiceFacade;
        this.furnitureQueryServiceFacade = furnitureQueryServiceFacade;
    }

    @GetMapping("/getByPrice")
    public ModelAndView getDealsByPrice(@RequestParam("minPrice") BigDecimal minPrice, @RequestParam("maxPrice") BigDecimal maxPrice) {
        ModelAndView modelAndView = new ModelAndView("dealsView");
        modelAndView.addObject("dealsList", dealQueryServiceFacade.getDealsByPrice(minPrice, maxPrice));
        modelAndView.addObject("dealTypes", DealType.values());
        return modelAndView;
    }

    @GetMapping("/getByType")
    public ModelAndView getDealsByDealType(@RequestParam("dealType") String dealType) {
        ModelAndView modelAndView = new ModelAndView("dealsView");
        modelAndView.addObject("dealsList", dealQueryServiceFacade.getDealsByType(DealType.valueOf(dealType)));
        modelAndView.addObject("dealTypes", DealType.values());
        return modelAndView;
    }

    @GetMapping("/getByName")
    public ModelAndView getDealsByName(@RequestParam("name") String name) {
        ModelAndView modelAndView = new ModelAndView("dealsView");
        modelAndView.addObject("dealsList", dealQueryServiceFacade.getDealsByName(name));
        modelAndView.addObject("dealTypes", DealType.values());
        return modelAndView;
    }

    @GetMapping("/getAll")
    public ModelAndView getAllDeals() {
        ModelAndView modelAndView = new ModelAndView("dealsView");
        modelAndView.addObject("dealsList", dealQueryServiceFacade.getAllDeals());
        modelAndView.addObject("dealTypes", DealType.values());
        return modelAndView;
    }

    @GetMapping("/showAddDealForm")
    public ModelAndView showAddDealForm(@RequestParam(value = "furnitureId") long furnitureId) {
        ModelAndView modelAndView = new ModelAndView("addDealView");
        modelAndView.addObject("furniture", furnitureQueryServiceFacade.getFurnitureById(furnitureId));
        modelAndView.addObject("dealTypes", DealType.values());
        modelAndView.addObject("dealDTO", new DealAddCommandDTO());
        return modelAndView;
    }

    @GetMapping
    public ModelAndView getAllAvailableDeals() {
        ModelAndView modelAndView = new ModelAndView("dealsView");
        modelAndView.addObject("dealTypes", DealType.values());
        modelAndView.addObject("dealsList", dealQueryServiceFacade.getAllAvailableDeals());
        return modelAndView;
    }
}