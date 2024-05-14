package uk.com.bookcloud.config.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 *
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.yaml")
@RequiredArgsConstructor
public class PersistenceJPAConfig {

    private final Environment environment;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("uk.com.bookcloud.entity");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));

//        dataSource.setUrl("jdbc:postgresql://gift-certificate-db.cvm48iyi6wut.ap-southeast-2.rds.amazonaws.com:5432/postgres");
//        dataSource.setUsername("postgres");
//        dataSource.setPassword("postgres");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");


        properties.setProperty("hibernate.ejb.event.post-insert", "org.hibernate.envers.event.spi.EnversPostInsertEventListenerImpl");
        properties.setProperty("hibernate.ejb.event.post-update", "org.hibernate.envers.event.spi.EnversPostUpdateEventListenerImpl");
        properties.setProperty("hibernate.ejb.event.post-delete", "org.hibernate.envers.event.spi.EnversPostDeleteEventListenerImpl");
        properties.setProperty("hibernate.ejb.event.pre-collection-update", "org.hibernate.envers.event.spi.EnversPreCollectionUpdateEventListenerImpl");
        properties.setProperty("hibernate.ejb.event.pre-collection-remove", "org.hibernate.envers.event.spi.EnversPreCollectionRemoveEventListenerImpl");
        properties.setProperty("hibernate.ejb.event.post-collection-recreate", "org.hibernate.envers.event.spi.EnversPostCollectionRecreateEventListenerImpl");

        return properties;
    }
}
