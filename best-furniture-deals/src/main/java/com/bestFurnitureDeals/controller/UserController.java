package com.bestFurnitureDeals.controller;

import com.bestFurnitureDeals.dto.model.UserDTO;
import com.bestFurnitureDeals.facade.UserServiceFacade;
import com.bestFurnitureDeals.model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserServiceFacade userServiceFacade;

    @Autowired
    public UserController(UserServiceFacade userServiceFacade) {
        this.userServiceFacade = userServiceFacade;
    }

    @PostMapping("/addUser")
    public ModelAndView addUser(@Valid UserDTO userDTO, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("addUserView");
            modelAndView.addObject("userTypes", UserType.values());
            modelAndView.addObject("errorMessage", "Invalid input!");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/login/showLoginForm");
        userServiceFacade.addUser(userDTO);
        return modelAndView;
    }

    @GetMapping("/signup")
    public ModelAndView showAddUserForm() {
        ModelAndView modelAndView = new ModelAndView("addUserView");
        modelAndView.addObject("userTypes", UserType.values());
        modelAndView.addObject("userDTO", new UserDTO());
        return modelAndView;
    }
}
