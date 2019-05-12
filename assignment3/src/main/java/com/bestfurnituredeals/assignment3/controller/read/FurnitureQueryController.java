package com.bestfurnituredeals.assignment3.controller.read;

import com.bestfurnituredeals.assignment3.facade.query.FurnitureQueryServiceFacade;
import com.bestfurnituredeals.assignment3.model.database.Furniture;
import com.bestfurnituredeals.assignment3.model.read.dto.FurnitureDTO;
import com.bestfurnituredeals.assignment3.model.write.FurnitureAddCommandDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/furniture")
public class FurnitureQueryController {
    private FurnitureQueryServiceFacade furnitureQueryServiceFacade;

    @Autowired
    public FurnitureQueryController(FurnitureQueryServiceFacade furnitureQueryServiceFacade) {
        this.furnitureQueryServiceFacade = furnitureQueryServiceFacade;
    }

    @GetMapping("/getAllFurniture")
    public ModelAndView getAllFurniture() {
        ModelAndView modelAndView = new ModelAndView("furnitureView");
        modelAndView.addObject("furnitureList", furnitureQueryServiceFacade.getAllFurniture());
        return modelAndView;
    }

    @GetMapping("/showAddFurnitureForm")
    public ModelAndView showAddFurnitureForm() {
        ModelAndView modelAndView = new ModelAndView("addFurnitureView");
        modelAndView.addObject("furnitureDTO", new FurnitureAddCommandDTO());
        return modelAndView;
    }

    @GetMapping("/showUpdateFurnitureForm/{id}")
    public ModelAndView showUpdateFurnitureForm(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("updateFurnitureView");
        modelAndView.addObject("furnitureDTO", furnitureQueryServiceFacade.getFurnitureById(id));
        return modelAndView;
    }
}
