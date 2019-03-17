package com.tuturugaNicolae.bestFurnitureDeals.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@ComponentScan({"com.tuturugaNicolae.bestFurnitureDeals"})
@PropertySource({"db.properties", "hibernate.properties"})
@EnableTransactionManagement
public class DatabaseConfiguration {
    private Environment environment;

    @Autowired
    public DatabaseConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource getDataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(environment.getProperty("db.driverClassName"));
            dataSource.setJdbcUrl(environment.getProperty("db.url"));
            dataSource.setUser(environment.getProperty("db.username"));
            dataSource.setPassword(environment.getProperty("db.password"));
            dataSource.setInitialPoolSize(environment.getProperty("connection.pool.initialPoolSize", Integer.class));
            dataSource.setMinPoolSize(environment.getProperty("connection.pool.minPoolSize", Integer.class));
            dataSource.setMaxPoolSize(environment.getProperty("connection.pool.maxPoolSize", Integer.class));
            dataSource.setMaxIdleTime(environment.getProperty("connection.pool.maxIdleTime", Integer.class));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource());
        sessionFactory.setPackagesToScan(environment.getProperty("hibernate.packagesToScan"));
        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager getHibernateTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }


    private Properties getHibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.showSQL"));
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        return hibernateProperties;
    }
}
