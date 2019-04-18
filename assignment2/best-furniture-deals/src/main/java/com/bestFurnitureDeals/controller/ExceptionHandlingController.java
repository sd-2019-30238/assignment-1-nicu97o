package com.bestFurnitureDeals.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlingController {
    @ExceptionHandler({Exception.class})
    public ModelAndView handleError(Exception exception) {
        ModelAndView modelAndView = new ModelAndView("errors");
        modelAndView.addObject("errorMessage", exception.getMessage());
        return modelAndView;
    }
}
