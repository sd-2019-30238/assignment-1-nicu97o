package com.bestfurnituredeals.assignment3.controller.read;

import com.bestfurnituredeals.assignment3.model.database.UserType;
import com.bestfurnituredeals.assignment3.model.write.UserRegisterCommandDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserQueryController {
    @GetMapping("/signup")
    public ModelAndView showAddUserForm() {
        ModelAndView modelAndView = new ModelAndView("addUserView");
        modelAndView.addObject("userTypes", UserType.values());
        modelAndView.addObject("userRegisterCommandDTO", new UserRegisterCommandDTO());
        return modelAndView;
    }
}
