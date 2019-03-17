package com.tuturugaNicolae.bestFurnitureDeals.presentation;

import com.tuturugaNicolae.bestFurnitureDeals.configuration.DatabaseConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DatabaseConfiguration.class);
    }
}
