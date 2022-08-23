package me.portfolioof.application.config;

import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfig.class);

    @Bean
    public DataSource init(@Value("${url}") String url, @Value("${username}") String user, @Value("${password}") String password) {
        LOGGER.info(url);
        LOGGER.info(user);
        LOGGER.info(password);
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        return dataSource;
    }
}
