package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.dao;

import com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.User;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.UserType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserDAOTest extends GenericDAOTest<User> {
    @Autowired
    private UserDAO userDAO;

    @Test
    public void testSelectAll_shouldBeSuccessful() {
        testSelectAll(userDAO, PreinsertedDataContainer.NUMBER_OF_PREINSERTED_USERS);
    }

    @Test
    public void testSelectById_shouldBeSuccessful() {
        testSelectById(userDAO, PreinsertedDataContainer.PREINSERTED_USER_1.getId(), PreinsertedDataContainer.PREINSERTED_USER_1);
    }

    @Test
    public void testInsertShouldBeSuccessful() {
        User user = new User("nicu2", "12345", "nicu@yahoo.com", UserType.STAFF);
        testInsert(userDAO, user, PreinsertedDataContainer.NUMBER_OF_PREINSERTED_USERS + 1);
    }

    @Test
    public void testUpdate_shouldBeSuccessful() {
        User user = new User(1l, "nicu", "123", "nicu.tuturuga@yahoo.com", UserType.STAFF);
        testUpdate(userDAO, user, user.getId());
    }

    @Test
    public void testDelete_shouldBeSuccessful() {
        testDelete(userDAO, PreinsertedDataContainer.PREINSERTED_USER_1.getId(), PreinsertedDataContainer.NUMBER_OF_PREINSERTED_USERS - 1);
    }

    @Test
    public void testFindUserByUsername_shouldBeSuccessful() {
        User user = userDAO.findByUsername(PreinsertedDataContainer.PREINSERTED_USER_1.getUsername()).get();
        assertThat(user, is(PreinsertedDataContainer.PREINSERTED_USER_1));
    }
}
