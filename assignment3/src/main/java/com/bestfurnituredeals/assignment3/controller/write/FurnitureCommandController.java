package com.bestfurnituredeals.assignment3.controller.write;

import com.bestfurnituredeals.assignment3.facade.command.FurnitureCommandServiceFacade;
import com.bestfurnituredeals.assignment3.model.write.FurnitureAddCommandDTO;
import com.bestfurnituredeals.assignment3.model.write.FurnitureUpdateCommandDTO;
import com.bestfurnituredeals.assignment3.service.command.FurnitureCommandService;
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
    private FurnitureCommandServiceFacade furnitureCommandServiceFacade;

    @Autowired
    public FurnitureCommandController(FurnitureCommandServiceFacade furnitureCommandServiceFacade) {
        this.furnitureCommandServiceFacade = furnitureCommandServiceFacade;
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteFurniture(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/furniture/getAllFurniture");
        furnitureCommandServiceFacade.deleteFurniture(id);
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
        furnitureCommandServiceFacade.addFurniture(furnitureDTO);
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
        furnitureCommandServiceFacade.updateFurniture(furnitureDTO);
        modelAndView.setViewName("redirect:/furniture/getAllFurniture");
        return modelAndView;
    }
}
