package com.tuturugaNicolae.bestFurnitureDeals.businessLogic.service;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.configuration.BusinessLogicConfiguration;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.exception.NoBoughtFurnitureFoundException;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.SecurityContext;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.BoughtFurnitureService;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao.BoughtFurnitureDAO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.BoughtFurniture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BusinessLogicConfiguration.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class BoughtFurnitureServiceTest {
    @Autowired
    private SecurityContext securityContext;
    @Autowired
    private BoughtFurnitureService boughtFurnitureService;
    @Autowired
    private BoughtFurnitureDAO boughtFurnitureDAO;

    @Before
    public void setUp() {
        securityContext.authenticate(PREINSERTED_USER_2.getUsername(), PREINSERTED_USER_2.getPassword());
    }

    @Test
    public void testAddNewFurnitureToCurrentClientOrder_shouldBeSuccessful() {
        boughtFurnitureService.addNewFurnitureToCurrentClientOrder(new BoughtFurniture(null, PREINSERTED_FURNITURE_1, 0, BigDecimal.ZERO, PREINSERTED_CLIENT_ORDER_1), PREINSERTED_CLIENT_ORDER_1);
        assertThat(boughtFurnitureDAO.selectAll().size(), is(NUMBER_OF_PREINSERTED_BOUGHT_FURNITURE + 1));
    }

    @Test
    public void testUpdateBoughtFurniture_shouldBeSuccessful() {
        BoughtFurniture boughtFurniture = boughtFurnitureService.getBoughtFurnitureById(PREINSERTED_BOUGHT_FURNITURE_1.getId());
        boughtFurniture.setBoughtQuantity(100);
        boughtFurniture.setPrice(BigDecimal.ZERO);
        assertThat(boughtFurniture, is(boughtFurnitureService.getBoughtFurnitureById(PREINSERTED_BOUGHT_FURNITURE_1.getId())));
    }

    @Test
    public void testGetAllBoughtFurnitureForCurrentClientOrder_shouldBeSuccessful() {
        List<BoughtFurniture> boughtFurniture = boughtFurnitureService.getAllBoughtFurnitureForCurrentClientOrder(PREINSERTED_CLIENT_ORDER_1);
        assertThat(boughtFurniture.size(), is(1));
        assertTrue(boughtFurniture.contains(PREINSERTED_BOUGHT_FURNITURE_1));
    }

    @Test
    public void testGetBoughtFurnitureById_shouldBeSuccessful() {
        BoughtFurniture boughtFurniture = boughtFurnitureService.getBoughtFurnitureById(PREINSERTED_BOUGHT_FURNITURE_1.getId());
        assertThat(boughtFurniture, is(PREINSERTED_BOUGHT_FURNITURE_1));
    }

    @Test(expected = NoBoughtFurnitureFoundException.class)
    public void testGetBoughtFurnitureByIdWhenNoFurnitureFound_shouldThrowException() {
        boughtFurnitureService.getBoughtFurnitureById(NUMBER_OF_PREINSERTED_FURNITURE + 1);
    }

    @Test
    public void testDeleteBoughtFurniture_shouldBeSuccessful() {
        boughtFurnitureService.deleteBoughtFurniture(PREINSERTED_BOUGHT_FURNITURE_1);
        assertThat(boughtFurnitureDAO.selectAll().size(), is(NUMBER_OF_PREINSERTED_BOUGHT_FURNITURE - 1));
    }
}
