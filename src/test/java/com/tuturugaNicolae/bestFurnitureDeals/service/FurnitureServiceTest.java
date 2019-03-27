package com.tuturugaNicolae.bestFurnitureDeals.service;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.configuration.BusinessLogicConfiguration;
import com.tuturugaNicolae.bestFurnitureDeals.exception.NoFurnitureFoundException;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.SecurityContext;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.Furniture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BusinessLogicConfiguration.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class FurnitureServiceTest {
    @Autowired
    private FurnitureService furnitureService;
    @Autowired
    private SecurityContext securityContext;

    @Before
    public void setUp() {
        securityContext.authenticate(PREINSERTED_USER_2.getUsername(), PREINSERTED_USER_2.getPassword());
    }


    @Test
    public void testGetFurnitureById_shouldBeSuccessful() {
        Furniture furniture = furnitureService.getFurnitureById(PREINSERTED_FURNITURE_1.getId());
        assertThat(furniture, is(PREINSERTED_FURNITURE_1));
    }

    @Test(expected = NoFurnitureFoundException.class)
    public void testGetFurnitureByIdWhenNoFurnitureWithWantedIdExists_shouldThrowException() {
        furnitureService.getFurnitureById(NUMBER_OF_PREINSERTED_FURNITURE + 1);
    }

    @Test
    public void testGetAllFurniture_shouldBeSuccessful() {
        List<Furniture> furniture = furnitureService.getAllFurniture();
        assertThat(furniture.size(), is(NUMBER_OF_PREINSERTED_FURNITURE));
    }

    @Test(expected = NoFurnitureFoundException.class)
    public void testGetAllFurnitureWhenThereIsNoFurniture_shouldThrowException() {
        for (Furniture furniture : furnitureService.getAllFurniture()) {
            furnitureService.deleteFurniture(furniture);
        }
        furnitureService.getAllFurniture();
    }

    @Test
    public void testUpdateFurniture_shouldBeSuccessful() {
        Furniture furniture = new Furniture(PREINSERTED_FURNITURE_1.getId(), "new", "new");
        furnitureService.updateFurniture(furniture);
        Furniture newFurniture = furnitureService.getFurnitureById(PREINSERTED_FURNITURE_1.getId());
        assertThat(newFurniture, is(furniture));
    }

    @Test
    public void testDeleteFurniture_shouldBeSuccessful() {
        furnitureService.deleteFurniture(PREINSERTED_FURNITURE_1);
        assertThat(furnitureService.getAllFurniture().size(), is(NUMBER_OF_PREINSERTED_FURNITURE - 1));
    }

    @Test
    public void testAddFurniture_shouldBeSuccessful() {
        furnitureService.addFurniture(new Furniture(0L, "new", "new"));
        assertThat(furnitureService.getAllFurniture().size(), is(NUMBER_OF_PREINSERTED_FURNITURE + 1));
    }
}
