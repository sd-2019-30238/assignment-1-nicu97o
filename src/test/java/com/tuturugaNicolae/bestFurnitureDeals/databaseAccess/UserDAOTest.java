package com.tuturugaNicolae.bestFurnitureDeals.databaseAccess;

import com.tuturugaNicolae.bestFurnitureDeals.model.User;
import com.tuturugaNicolae.bestFurnitureDeals.model.UserType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class UserDAOTest extends GenericDAOTest<User> {
    @Autowired
    private UserDAO userDAO;

    @Test
    public void testSelectAll_shouldBeSuccessful() {
        testSelectAll(userDAO, NUMBER_OF_PREINSERTED_USERS);
    }

    @Test
    public void testSelectById_shouldBeSuccessful() {
        testSelectById(userDAO, PREINSERTED_USER_1.getId(), PREINSERTED_USER_1);
    }

    @Test
    public void testInsertShouldBeSuccessful() {
        User user = new User("nicu", "123", "nicu.tuturuga@yahoo.com", BigDecimal.ZERO, UserType.STAFF);
        testInsert(userDAO, user, NUMBER_OF_PREINSERTED_USERS + 1);
    }

    @Test
    public void testUpdate_shouldBeSuccessful() {
        User user = new User(1l, "nicu", "123", "nicu.tuturuga@yahoo.com", BigDecimal.ZERO, UserType.STAFF);
        testUpdate(userDAO, user, user.getId());
    }

    @Test
    public void testDelete_shouldBeSuccessful() {
        testDelete(userDAO, userDAO.selectById(PREINSERTED_USER_1.getId()), NUMBER_OF_PREINSERTED_USERS - 1);
    }
}
