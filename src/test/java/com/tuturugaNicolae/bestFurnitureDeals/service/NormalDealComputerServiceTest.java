package com.tuturugaNicolae.bestFurnitureDeals.service;

import com.tuturugaNicolae.bestFurnitureDeals.service.impl.NormalDealComputerService;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.BoughtFurniture;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer.PREINSERTED_CLIENT_ORDER_1;
import static com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer.PREINSERTED_DEAL_1;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class NormalDealComputerServiceTest {

    private DealComputerService dealComputerService;

    @Before
    public void setUp() {
        dealComputerService = new NormalDealComputerService();
    }

    @Test
    public void testComputeFurnitureOrderDetails_shouldBeSuccessful() {
        BoughtFurniture boughtFurniture = dealComputerService.computeFurnitureOrderDetails(PREINSERTED_CLIENT_ORDER_1, PREINSERTED_DEAL_1, 2);
        assertThat(boughtFurniture.getPrice(), is(PREINSERTED_DEAL_1.getPrice().multiply(BigDecimal.valueOf(2))));
    }
}
