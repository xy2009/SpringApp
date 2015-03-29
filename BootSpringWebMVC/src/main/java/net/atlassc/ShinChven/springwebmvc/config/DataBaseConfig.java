package net.atlassc.ShinChven.springwebmvc.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;


/**
 * Created by ShinChven on 15/3/29.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"net.atlassc.ShinChven.springwebmvc.repository"})
public class DataBaseConfig {

    @Autowired
    private Environment env;

    @Value("$init-db:false")
    private String initDataBase;

    @Bean
    DataSource dataSource() {

        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/SpringBoot?useUnicode=true&amp;characterEncoding=utf-8&amp;autoReconnect=true&amp;failOverReadOnly=false");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");

        return dataSource;
    }

    @Bean
    LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.format_sql", "true");
        sessionFactoryBean.setHibernateProperties(properties);
        return sessionFactoryBean;
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean managerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        managerFactoryBean.setDataSource(dataSource());

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(Boolean.TRUE);
        vendorAdapter.setShowSql(Boolean.TRUE);
        vendorAdapter.setDatabase(Database.MYSQL);

        managerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        managerFactoryBean.setPackagesToScan("net.atlassc.ShinChven.springwebmvc.entity");
        return managerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        EntityManagerFactory factory = entityManagerFactory().getObject();
        return new JpaTransactionManager(factory);
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator(){
        return new HibernateExceptionTranslator();
    }


//    /**
//     * druid dataSource
//     * @return
//     */
//    @Bean
//    public DataSource dataSource() {
//
//        DruidDataSource dataSource = new DruidDataSource();
//
//        // The basic properties of URL, user, password
//        dataSource.setUrl("jdbc:mysql://localhost:3306/SpringBoot?useUnicode=true&amp;characterEncoding=utf-8&amp;autoReconnect=true&amp;failOverReadOnly=false");
//        dataSource.setUsername("root");
//        dataSource.setPassword("1234");
//
//        // The configuration initialization size, minimum, maximum
//        dataSource.setInitialSize(1);
//        dataSource.setMinIdle(1);
//        dataSource.setMaxActive(20);
//
//        // Configuration for connection timeout - wait
//        dataSource.setMaxWait(60000);
//
//        // How long before the allocation interval a detection,
//        // detection needs to shut down the free connection, in milliseconds
//        dataSource.setTimeBetweenEvictionRunsMillis(60000);
//
//        // To configure a connection minimum survival time in the pool, in milliseconds
//        dataSource.setMinEvictableIdleTimeMillis(300000);
//
//        dataSource.setValidationQuery("SELECT 'X'");
//        dataSource.setTestWhileIdle(true);
//        dataSource.setTestOnBorrow(false);
//        dataSource.setTestOnReturn(false);
//
//        // Open PSCache, and specifies the size of each PSCache connection
//        dataSource.setPoolPreparedStatements(true);
//        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
//
//        // Configuration monitoring statistics to intercept filters,
//        // removed SQL unable to statistics, monitoring interface
//        try {
//            dataSource.setFilters("stat");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return dataSource;
//    }


}
