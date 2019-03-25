package com.tuturugaNicolae.bestFurnitureDeals.presentation;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.configuration.BusinessLogicConfiguration;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.UserDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.UserTypeDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BusinessLogicConfiguration.class);
        UserService userService = applicationContext.getBean("userServiceImpl", UserService.class);
        userService.addUser(new UserDTO("tuturuga", "tuturuganicu@gmail.com", UserTypeDTO.STAFF), "12345");
        userService.addUser(new UserDTO("nicu", "nicu@gmail.com", UserTypeDTO.CLIENT), "12345");
        SwingUtilities.invokeLater(() -> new LoginFrame(applicationContext));
    }
}
