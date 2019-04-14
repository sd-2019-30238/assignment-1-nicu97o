package com.bestFurnitureDeals;

import com.bestFurnitureDeals.service.ClientOrderService;
import com.bestFurnitureDeals.service.DealService;
import com.bestFurnitureDeals.service.FurnitureService;
import com.bestFurnitureDeals.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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


    public static void main(String[] args) {
        SpringApplication.run(BestFurnitureDealsApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo() {
        return (args -> {
//            userDAO.addUser(new User(1L, "nicu", "1234", "tuturuganicu@gmail.com", UserType.CLIENT, new ArrayList<>()));
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
