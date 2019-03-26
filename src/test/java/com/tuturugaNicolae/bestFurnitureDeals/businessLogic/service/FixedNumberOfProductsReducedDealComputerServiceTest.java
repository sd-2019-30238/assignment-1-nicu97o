package com.tuturugaNicolae.bestFurnitureDeals.businessLogic.service;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.DealComputerService;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.impl.FixedNumberOfProductsReducedDealComputerService;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.impl.PayOneAndGetTwoDealComputerService;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.BoughtFurniture;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer.PREINSERTED_CLIENT_ORDER_1;
import static com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer.PREINSERTED_DEAL_1;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FixedNumberOfProductsReducedDealComputerServiceTest {
    private DealComputerService dealComputerService;

    @Before
    public void setUp() {
        dealComputerService = new FixedNumberOfProductsReducedDealComputerService();
    }

    @Test
    public void testComputeFurnitureOrderDetails_shouldBeSuccessful() {
        BoughtFurniture boughtFurniture = dealComputerService.computeFurnitureOrderDetails(PREINSERTED_CLIENT_ORDER_1, PREINSERTED_DEAL_1, 3);
        assertThat(boughtFurniture.getPrice(), is(PREINSERTED_DEAL_1.getPrice().multiply(BigDecimal.valueOf(3)).multiply(BigDecimal.valueOf(0.75))));
    }
}
