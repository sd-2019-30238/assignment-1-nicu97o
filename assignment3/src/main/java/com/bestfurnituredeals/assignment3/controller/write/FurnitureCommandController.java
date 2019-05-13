package com.bestfurnituredeals.assignment3.controller.write;

import com.bestfurnituredeals.assignment3.mediator.Mediator;
import com.bestfurnituredeals.assignment3.model.write.FurnitureAddCommandDTO;
import com.bestfurnituredeals.assignment3.model.write.FurnitureUpdateCommandDTO;
import com.bestfurnituredeals.assignment3.request.Request;
import com.bestfurnituredeals.assignment3.request.RequestType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/furniture")
public class FurnitureCommandController {
    private Mediator mediator;

    @Autowired
    public FurnitureCommandController(Mediator mediator) {
        this.mediator = mediator;
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteFurniture(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/furniture/getAllFurniture");
        mediator.handle(new Request("deleteFurniture", id, RequestType.FURNITURE_COMMAND));
        return modelAndView;
    }

    @PostMapping
    public ModelAndView addFurniture(@Valid FurnitureAddCommandDTO furnitureDTO, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("redirect:/furniture/getAllFurniture");
        if (result.hasErrors()) {
            modelAndView.setViewName("addFurnitureView");
            modelAndView.addObject("errorMessage", "Invalid input!");
            return modelAndView;
        }
        mediator.handle(new Request("addFurniture", furnitureDTO, RequestType.FURNITURE_COMMAND));
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView updateFurniture(@Valid FurnitureUpdateCommandDTO furnitureDTO, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("updateFurnitureView");
            modelAndView.addObject("errorMessage", "Invalid input!");
            return modelAndView;
        }
        mediator.handle(new Request("updateFurniture", furnitureDTO, RequestType.FURNITURE_COMMAND));
        modelAndView.setViewName("redirect:/furniture/getAllFurniture");
        return modelAndView;
    }
}
