package ru.reboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.reboot.dao.AuthRepository;
import ru.reboot.dao.AuthRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class Config {

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Bean
    public AuthRepository userRepositoryBean() throws SQLException {

        Connection connection = null;//DriverManager.getConnection(url, username, password);
        return new AuthRepositoryImpl(connection);
    }
}
