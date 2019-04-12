package com.bestFurnitureDeals.controller;

import com.bestFurnitureDeals.dto.model.DealDTO;
import com.bestFurnitureDeals.facade.DealServiceFacade;
import com.bestFurnitureDeals.facade.FurnitureServiceFacade;
import com.bestFurnitureDeals.model.DealType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/deals")
public class DealController {
    private DealServiceFacade dealServiceFacade;
    private FurnitureServiceFacade furnitureServiceFacade;

    @Autowired
    public DealController(DealServiceFacade dealServiceFacade, FurnitureServiceFacade furnitureServiceFacade) {
        this.dealServiceFacade = dealServiceFacade;
        this.furnitureServiceFacade = furnitureServiceFacade;
    }

    @GetMapping("/showAddDealForm")
    public ModelAndView showAddDealForm(@RequestParam(value = "furnitureId") long furnitureId) {
        ModelAndView modelAndView = new ModelAndView("addDealView");
        modelAndView.addObject("furniture", furnitureServiceFacade.getFurnitureById(furnitureId));
        modelAndView.addObject("dealTypes", DealType.values());
        modelAndView.addObject("dealDTO", new DealDTO());
        return modelAndView;
    }

    @GetMapping
    public ModelAndView getAllAvailableDeals() {
        ModelAndView modelAndView = new ModelAndView("dealsView");
        modelAndView.addObject("dealTypes", DealType.values());
        modelAndView.addObject("dealsList", dealServiceFacade.getAllAvailableDeals());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView addDeal(@RequestParam(value = "furnitureId") long furnitureId, @Valid DealDTO dealDTO, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("errors");
            modelAndView.addObject("errorMessage", result.getFieldErrors().stream().map(o -> o.getDefaultMessage()).collect(Collectors.toList()));
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/deals/showAddDealForm?furnitureId=" + furnitureId);
        dealServiceFacade.addDeal(dealDTO, furnitureId);
        return modelAndView;
    }

    @GetMapping("/getAll")
    public ModelAndView getAllDeals() {
        ModelAndView modelAndView = new ModelAndView("dealsView");
        modelAndView.addObject("dealsList", dealServiceFacade.getAllDeals());
        modelAndView.addObject("dealTypes", DealType.values());
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteDeal(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/deals");
        dealServiceFacade.deleteDeal(id);
        return modelAndView;
    }

    @GetMapping("/getByPrice")
    public ModelAndView getDealsByPrice(@RequestParam("minPrice") BigDecimal minPrice, @RequestParam("maxPrice") BigDecimal maxPrice) {
        ModelAndView modelAndView = new ModelAndView("dealsView");
        modelAndView.addObject("dealsList", dealServiceFacade.getDealsByPrice(minPrice, maxPrice));
        modelAndView.addObject("dealTypes", DealType.values());
        return modelAndView;
    }

    @GetMapping("/getByType")
    public ModelAndView getDealsByDealType(@RequestParam("dealType") String dealType) {
        ModelAndView modelAndView = new ModelAndView("dealsView");
        modelAndView.addObject("dealsList", dealServiceFacade.getDealsByType(DealType.valueOf(dealType)));
        modelAndView.addObject("dealTypes", DealType.values());
        return modelAndView;
    }

    @GetMapping("/getByName")
    public ModelAndView getDealsByName(@RequestParam("name") String name) {
        ModelAndView modelAndView = new ModelAndView("dealsView");
        modelAndView.addObject("dealsList", dealServiceFacade.getDealsByName(name));
        modelAndView.addObject("dealTypes", DealType.values());
        return modelAndView;
    }
}
