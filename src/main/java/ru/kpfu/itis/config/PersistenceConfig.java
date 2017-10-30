package ru.kpfu.itis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("ru.kpfu.itis.repo")
@EnableTransactionManagement
@PropertySource("classpath:ru.kpfu.itis/jdbc.properties")
public class PersistenceConfig {

    @Autowired
    private Environment environment;

    @Bean
    PlatformTransactionManager transactionManager(){
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }

    private LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("ru.kpfu.itis.model");
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL94Dialect");
        jpaProperties.put("hibernate.hbm2ddl.auto", "create");
        return entityManagerFactoryBean;
    }

    private HibernateJpaVendorAdapter hibernateJpaVendorAdapter(){
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        return hibernateJpaVendorAdapter;
    }

    private DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("driver"));
        dataSource.setUrl(environment.getProperty("url"));
        dataSource.setUsername(environment.getProperty("username"));
        dataSource.setPassword(environment.getProperty("password"));
        return dataSource;
    }


}
