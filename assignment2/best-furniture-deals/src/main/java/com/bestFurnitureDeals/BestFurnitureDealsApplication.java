package com.bestFurnitureDeals;

import com.bestFurnitureDeals.model.ClientOrder;
import com.bestFurnitureDeals.model.User;
import com.bestFurnitureDeals.model.UserObserver;
import com.bestFurnitureDeals.model.UserType;
import com.bestFurnitureDeals.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class BestFurnitureDealsApplication {
    @Autowired
    private UserService userDAO;
    @Autowired
    private FurnitureService furnitureService;
    @Autowired
    private DealService dealService;
    @Autowired
    private ClientOrderService clientOrderService;
    @Autowired
    private ObserverService<UserObserver, ClientOrder> observerService;


    public static void main(String[] args) {
        SpringApplication.run(BestFurnitureDealsApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo() {
        return (args -> {
                userDAO.addUser(new User(1L, "nicu", "1234", "tuturuganicu@gmail.com", UserType.STAFF, new ArrayList<>()));
//            observerService.addObserver(clientOrderService.getCurrentClientOrderForAnUser("nicu").getId(), new UserObserver(0l,"tuturuganicu@gmail.com"));
//            observerService.notifyObservers(1l);
            ////            System.out.println(userDAO.findById(1L));
////            System.out.println(userDAO.findAll());
//            //userDAO.delete(new User(1L, "nicu97o", "nicu", "nicu@gmail.com", UserType.CLIENT, new ArrayList<>()));
//            //System.out.println(userDAO.findAll());
//            furnitureService.addFurniture(new Furniture("Furniture 1", "My desc"));
//            dealService.addDeal(new Deal("Deal1", DealType.NORMAL, BigDecimal.ONE,true,1), 1l);
//            dealService.addDeal(new Deal("Deal2", DealType.NORMAL, BigDecimal.TEN,false,1), 1l);
        });
    }

}
