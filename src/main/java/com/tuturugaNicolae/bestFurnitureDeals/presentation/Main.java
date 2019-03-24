package com.tuturugaNicolae.bestFurnitureDeals.presentation;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.configuration.BusinessLogicConfiguration;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.UserDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.SecurityContext;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.UserService;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.configuration.DatabaseConfiguration;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.UserType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext2 = new AnnotationConfigApplicationContext(DatabaseConfiguration.class);
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BusinessLogicConfiguration.class);
        UserService userService = applicationContext.getBean("userServiceImpl", UserService.class);
        SecurityContext securityContext = applicationContext.getBean("securityContext", SecurityContext.class);
        userService.addUser(new UserDTO("tuturuga", "tuturuganicu@gmail.com", UserType.CLIENT), "123456");
        new Thread(() -> {
            securityContext.authenticate("tuturuga", "123456");
            System.out.println(securityContext.getLoggedUser().get());
            System.out.println(userService.getAllUsers());
            new Scanner(System.in).nextLine();
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            System.out.println(userService.getAllUsers());
            new Scanner(System.in).nextLine();
        }).start();
    }
}
