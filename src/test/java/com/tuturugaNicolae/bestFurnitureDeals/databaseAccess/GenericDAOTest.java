package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess;

import com.tuturugaNicolae.bestFurnitureDeals.configuration.AppConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * This class contains the principal methods for testing a dao's operations (create, read, update, delete) that are specific for all daos.
 * It also contains all the data loaded in the database to be used in the test classes that extends this class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfiguration.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
public abstract class GenericDAOTest<T> {
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
