package ru.reboot.dao;

import ru.reboot.dto.User;

import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.util.List;

/**
 * Auth repository.
 */
public class AuthRepositoryImpl implements AuthRepository {

    private final Connection connection;

    public AuthRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User findUserByUserId(String userId) {
        return null;
    }

    @Override
    public User findUserByLogin(String login) {
        return null;
    }

    @Override
    public void deleteUserId(String userId) {

    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @PreDestroy
    public void onClose() {
        try {
            connection.close();
        } catch (Exception ex) {
        }
    }
}
