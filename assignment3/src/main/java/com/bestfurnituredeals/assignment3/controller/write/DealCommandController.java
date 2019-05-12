package com.bestfurnituredeals.assignment3.controller.write;

import com.bestfurnituredeals.assignment3.facade.command.DealCommandServiceFacade;
import com.bestfurnituredeals.assignment3.model.write.DealAddCommandDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/deals")
public class DealCommandController {
    private DealCommandServiceFacade dealCommandServiceFacade;

    @Autowired
    public DealCommandController(DealCommandServiceFacade dealCommandServiceFacade) {
        this.dealCommandServiceFacade = dealCommandServiceFacade;
    }

    @PostMapping
    public ModelAndView addDeal(@RequestParam(value = "furnitureId") long furnitureId, @Valid DealAddCommandDTO dealDTO, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("errors");
            modelAndView.addObject("errorMessage", result.getFieldErrors().stream().map(o -> o.getDefaultMessage()).collect(Collectors.toList()));
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/deals/showAddDealForm?furnitureId=" + furnitureId);
        dealCommandServiceFacade.addDeal(dealDTO, furnitureId);
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteDeal(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/deals");
        dealCommandServiceFacade.deleteDeal(id);
        return modelAndView;
    }
}
