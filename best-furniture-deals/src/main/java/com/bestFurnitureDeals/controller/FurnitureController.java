package com.bestFurnitureDeals.controller;

import com.bestFurnitureDeals.dto.model.FurnitureDTO;
import com.bestFurnitureDeals.facade.FurnitureServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/furniture")
public class FurnitureController {
    private FurnitureServiceFacade furnitureServiceFacade;

    @Autowired
    public FurnitureController(FurnitureServiceFacade furnitureServiceFacade) {
        this.furnitureServiceFacade = furnitureServiceFacade;
    }

    @GetMapping("/getAllFurniture")
    public ModelAndView getAllFurniture() {
        ModelAndView modelAndView = new ModelAndView("furnitureView");
        modelAndView.addObject("furnitureList", furnitureServiceFacade.getAllFurniture());
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteFurniture(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/furniture/getAllFurniture");
        furnitureServiceFacade.deleteFurniture(id);
        return modelAndView;
    }

    @GetMapping("/showAddFurnitureForm")
    public ModelAndView showAddFurnitureForm() {
        ModelAndView modelAndView = new ModelAndView("addFurnitureView");
        modelAndView.addObject("furnitureDto", new FurnitureDTO());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView addFurniture(@Valid FurnitureDTO furnitureDTO, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("redirect:/furniture/getAllFurniture");
        if (result.hasErrors()) {
            modelAndView.setViewName("addFurnitureView");
            modelAndView.addObject("errorMessage", "Invalid input!");
            return modelAndView;
        }
        furnitureServiceFacade.addFurniture(furnitureDTO);
        return modelAndView;
    }

    @GetMapping("/showUpdateFurnitureForm/{id}")
    public ModelAndView showUpdateFurnitureForm(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("updateFurnitureView");
        modelAndView.addObject("furniture", furnitureServiceFacade.getFurnitureById(id));
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView updateFurniture(@Valid FurnitureDTO furnitureDTO, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("updateFurnitureView");
            modelAndView.addObject("errorMessage", "Invalid input!");
            return modelAndView;
        }
        furnitureServiceFacade.updateFurniture(furnitureDTO);
        modelAndView.setViewName("redirect:/furniture/getAllFurniture");
        return modelAndView;
    }
}
