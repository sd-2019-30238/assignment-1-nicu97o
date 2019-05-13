package com.bestfurnituredeals.assignment3.controller.write;

import com.bestfurnituredeals.assignment3.mediator.Mediator;
import com.bestfurnituredeals.assignment3.model.write.DealAddCommandDTO;
import com.bestfurnituredeals.assignment3.request.Request;
import com.bestfurnituredeals.assignment3.request.RequestType;
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
    private Mediator mediator;

    @Autowired
    public DealCommandController(Mediator mediator) {
        this.mediator = mediator;
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
        dealDTO.setFurnitureId(furnitureId);
        mediator.handle(new Request("addDeal", dealDTO, RequestType.DEAL_COMMAND));
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteDeal(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/deals");
        mediator.handle(new Request("deleteDeal", id, RequestType.DEAL_COMMAND));
        return modelAndView;
    }
}
