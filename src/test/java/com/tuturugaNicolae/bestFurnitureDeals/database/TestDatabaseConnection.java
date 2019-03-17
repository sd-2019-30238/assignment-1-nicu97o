package com.tuturugaNicolae.bestFurnitureDeals.database;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class TestDatabaseConnection {
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/bestFurnitureDeals?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private static final int INITIAL_POOL_SIZE = 5;
    private static final int MIN_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 20;
    private static final int MAX_IDLE_TIME = 3000;
    private ComboPooledDataSource dataSource;

    @Before
    public void setUp() {
        dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(DRIVER_CLASS);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        dataSource.setJdbcUrl(URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        dataSource.setInitialPoolSize(INITIAL_POOL_SIZE);
        dataSource.setMinPoolSize(MIN_POOL_SIZE);
        dataSource.setMaxPoolSize(MAX_POOL_SIZE);
        dataSource.setMaxIdleTime(MAX_IDLE_TIME);
    }

    @Test
    public void testGettingAConnectionFromDatabase() {
        boolean noErrorsFound = true;
        try {
            dataSource.getConnection();
        } catch (SQLException e) {
            noErrorsFound = false;
        }
        assertTrue(noErrorsFound);
    }
}
