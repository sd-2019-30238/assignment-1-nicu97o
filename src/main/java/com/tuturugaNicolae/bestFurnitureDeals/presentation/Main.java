package com.tuturugaNicolae.bestFurnitureDeals.presentation;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.configuration.BusinessLogicConfiguration;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.UserService;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.User;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.UserType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BusinessLogicConfiguration.class);
//        UserService userService = applicationContext.getBean("userServiceImpl", UserService.class);
//        userService.addUser(new User(null, "tuturuga", "12345", "tuturuganicu@gmail.com", UserType.STAFF), "12345");
//        userService.addUser(new User(null, "nicu", "12345", "nicu@gmail.com", UserType.CLIENT), "12345");
        SwingUtilities.invokeLater(() -> new LoginFrame(applicationContext));
    }
}
