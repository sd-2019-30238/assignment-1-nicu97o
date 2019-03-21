package com.tuturugaNicolae.bestFurnitureDeals.service;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.UserService;
import com.tuturugaNicolae.bestFurnitureDeals.configuration.DatabaseConfiguration;
import com.tuturugaNicolae.bestFurnitureDeals.exception.NoRightsToPerformThisOperationException;
import com.tuturugaNicolae.bestFurnitureDeals.exception.NoUserFoundException;
import com.tuturugaNicolae.bestFurnitureDeals.model.User;
import com.tuturugaNicolae.bestFurnitureDeals.model.UserType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer.NUMBER_OF_PREINSERTED_USERS;
import static com.tuturugaNicolae.bestFurnitureDeals.container.PreinsertedDataContainer.PREINSERTED_USER_1;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatabaseConfiguration.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testGetUserById_shouldBeSuccessful() {
        User user = userService.getUserById(PREINSERTED_USER_1.getId());
        assertThat(user, is(PREINSERTED_USER_1));
    }

    @Test(expected = NoUserFoundException.class)
    public void testGetUserByIdWhenNoUserWithWantedIdExists_shouldThrowException() {
        userService.getUserById(-1);
    }

    @Test
    public void testGetUserByUsername_shouldBeSuccessful() {
        User user = userService.getUserByUsername(PREINSERTED_USER_1.getUsername());
        assertThat(user, is(PREINSERTED_USER_1));
    }

    @Test(expected = NoUserFoundException.class)
    public void testGetUserByUsernameWhenNoUserWithWantedUsernameExists_shouldThrowException() {
        userService.getUserByUsername("test");
    }

    @Test
    public void testGetAllUsers_shouldBeSuccessful() {
        List<User> users = userService.getAllUsers();
        assertThat(users.size(), is(NUMBER_OF_PREINSERTED_USERS));
        assertTrue(users.contains(PREINSERTED_USER_1));
    }

    @Test
    public void testUpdateUser_shouldBeSuccessful() {
        User user = new User(PREINSERTED_USER_1.getId(), PREINSERTED_USER_1.getUsername(), PREINSERTED_USER_1.getPassword() + "New", PREINSERTED_USER_1.getMail() + "New", PREINSERTED_USER_1.getUserType());
        userService.updateUser(user, PREINSERTED_USER_1.getUsername());
        User updatedUser = userService.getUserByUsername(PREINSERTED_USER_1.getUsername());
        assertThat(updatedUser, is(user));
    }

    @Test(expected = NoRightsToPerformThisOperationException.class)
    public void testUpdateUserWhenLoggedUsernameIsDifferentFromUserToUpdate_shouldThrowException() {
        User user = new User(PREINSERTED_USER_1.getUsername() + "New", PREINSERTED_USER_1.getPassword() + "New", PREINSERTED_USER_1.getMail() + "New", PREINSERTED_USER_1.getUserType());
        userService.updateUser(user, PREINSERTED_USER_1.getUsername());
    }

    @Test
    public void testDeleteUser_shouldBeSuccessful() {
        userService.deleteUser(PREINSERTED_USER_1.getUsername());
        List<User> usersLeftAfterDelete = userService.getAllUsers();
        assertThat(usersLeftAfterDelete.size(), is(NUMBER_OF_PREINSERTED_USERS - 1));
        assertTrue(!usersLeftAfterDelete.contains(PREINSERTED_USER_1));
    }

    @Test
    public void testAddUser_shouldBeSuccessful() {
        User user = new User(NUMBER_OF_PREINSERTED_USERS + 1L, "test", "test123", "test@yahoo.com", UserType.CLIENT);
        userService.addUser(user);
        List<User> users = userService.getAllUsers();
        assertThat(users.size(), is(NUMBER_OF_PREINSERTED_USERS + 1));
        assertTrue(users.contains(user));
    }
}
