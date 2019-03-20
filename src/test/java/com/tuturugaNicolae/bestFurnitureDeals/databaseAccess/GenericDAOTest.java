package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess;

import com.tuturugaNicolae.bestFurnitureDeals.configuration.DatabaseConfiguration;
import com.tuturugaNicolae.bestFurnitureDeals.model.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * This class contains the principal methods for testing a dao's operations (create, read, update, delete) that are specific for all daos.
 * It also contains all the data loaded in the database to be used in the test classes that extends this class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatabaseConfiguration.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
public abstract class GenericDAOTest<T> {
    protected static final User PREINSERTED_USER_1 = new User(1L, "tuturuga", "12345", "tuturuganicu@gmail.com", UserType.CLIENT);
    protected static final int NUMBER_OF_PREINSERTED_USERS = 1;

    protected static final OrderHistory PREINSERTED_ORDER_HISTORY_1 = new OrderHistory(1L, LocalDateTime.of(2019, 1, 1, 0, 0), OrderState.PLACED);
    protected static final int NUMBER_OF_PREINSERTED_ORDER_HISTORY = 1;

    protected static final Furniture PREINSERTED_FURNITURE_1 = new Furniture(1L, "Chair", "Strong material");
    protected static final int NUMBER_OF_PREINSERTED_FURNITURE = 1;

    protected static final FeedbackMessage PREINSERTED_FEEDBACK_MESSAGE_1 = new FeedbackMessage(1L, "Feedback", "Good product");
    protected static final int NUMBER_OF_PREINSERTED_FEEDBACK_MESSAGES = 1;

    protected static final Deal PREINSERTED_DEAL_1 = new Deal(1L, "Chair", DealType.REDUCED, PREINSERTED_FURNITURE_1, BigDecimal.TEN, true, 1);
    protected static final Deal PREINSERTED_DEAL_2 = new Deal(2L, "Chair 2", DealType.REDUCED, PREINSERTED_FURNITURE_1, BigDecimal.ONE, true, 1);
    protected static final int NUMBER_OF_PREINSERTED_DEALS = 2;

    protected static final BoughtFurniture PREINSERTED_BOUGHT_FURNITURE_1 = new BoughtFurniture(1L, 1, BigDecimal.TEN);
    protected static final int NUMBER_OF_PREINSERTED_BOUGHT_FURNITURE = 1;

    protected static final ClientOrder PREINSERTED_CLIENT_ORDER_1 = new ClientOrder(1L, false, PaymentMethod.CASH, BigDecimal.TEN);
    protected static final ClientOrder PREINSERTED_CLIENT_ORDER_WITHOUT_ORDER_HISTORY = new ClientOrder(2L, false, PaymentMethod.CASH, BigDecimal.TEN);
    protected static final int NUMBER_OF_PREINSERTED_CLIENT_ORDERS = 2;

    public void testSelectById(GenericDAO genericDAO, long id, T objectToCompareWith) {
        T t = (T) genericDAO.selectById(id);
        assertEquals(objectToCompareWith, t);
    }

    public void testSelectAll(GenericDAO genericDAO, int numberOfExpectedObjects) {
        List<T> resultedList = genericDAO.selectAll();
        assertThat(resultedList.size(), is(numberOfExpectedObjects));
    }

    public void testInsert(GenericDAO genericDAO, T objectToInsert, int expectedNumberOfObjectsAfterInsert) {
        genericDAO.insert(objectToInsert);
        List<T> resultedList = genericDAO.selectAll();
        assertThat(resultedList.size(), is(expectedNumberOfObjectsAfterInsert));
    }

    public void testUpdate(GenericDAO genericDAO, T objectToUpdate, long idOfObjectToUpdate) {
        genericDAO.update(objectToUpdate);
        T updatedObject = (T) genericDAO.selectById(idOfObjectToUpdate);
        assertEquals(updatedObject, objectToUpdate);
    }

    public void testDelete(GenericDAO genericDAO, long idOfObjectToDelete, int expectedNumberOfObjectsAfterDelete) {
        T objectToDelete = (T) genericDAO.selectById(idOfObjectToDelete);
        genericDAO.delete(objectToDelete);
        List<T> resultedList = genericDAO.selectAll();
        assertThat(resultedList.size(), is(expectedNumberOfObjectsAfterDelete));
    }
}
