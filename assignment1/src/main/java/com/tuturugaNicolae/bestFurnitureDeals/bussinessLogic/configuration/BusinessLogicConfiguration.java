package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.configuration;

import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.configuration.DatabaseConfiguration;
import org.springframework.context.annotation.*;

@Configuration
@Import(DatabaseConfiguration.class)
@ComponentScan({"com.tuturugaNicolae.bestFurnitureDeals"})
@EnableAspectJAutoProxy
public class BusinessLogicConfiguration {
}
