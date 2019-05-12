package com.bestfurnituredeals.assignment3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping("/showLoginForm")
    public ModelAndView showLoginForm() {
        return new ModelAndView("loginFormView");
    }
}
