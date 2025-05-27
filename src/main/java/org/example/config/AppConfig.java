package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("org.example")
@PropertySource("/application.properties")
public class AppConfig {
    @Value("${DB_DRIVER}")
    private String dbDriver;
    @Value("${DB_URL}")
    private String dbUrl;
    @Value("${DB_USERNAME}")
    private String dbUser;
    @Value("${DB_PASSWORD}")
    private String dbPassword;


    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dbDriver);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPassword);
        return dataSource;
    }
    @Bean
    public JdbcTemplate getTemplate(){
        return new JdbcTemplate(getDataSource());
    }
}
