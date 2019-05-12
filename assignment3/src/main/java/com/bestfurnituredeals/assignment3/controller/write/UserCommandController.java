package com.bestfurnituredeals.assignment3.controller.write;

import com.bestfurnituredeals.assignment3.request.Request;
import com.bestfurnituredeals.assignment3.request.RequestType;
import com.bestfurnituredeals.assignment3.mediator.Mediator;
import com.bestfurnituredeals.assignment3.model.database.UserType;
import com.bestfurnituredeals.assignment3.model.write.UserRegisterCommandDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserCommandController {
    private Mediator mediator;

    @Autowired
    public UserCommandController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/addUser")
    public ModelAndView addUser(@Valid UserRegisterCommandDTO userRegisterCommandDTO, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("addUserView");
            modelAndView.addObject("userTypes", UserType.values());
            modelAndView.addObject("errorMessage", "Invalid input!");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/login/showLoginForm");
        mediator.handle(new Request("addUser", userRegisterCommandDTO, RequestType.USER_COMMAND));
        return modelAndView;
    }
}
