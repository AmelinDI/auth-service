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
    public User findItem(String itemId) {
        return null;
    }

    @Override
    public User findAllItemsByCategory(String category) {
        return null;
    }

    @Override
    public void deleteItem(String itemId) {

    }

    @Override
    public User createItem(User user) {
        return null;
    }

    @Override
    public User updateItem(User user) {
        return null;
    }

    @Override
    public List<User> getAllItems() {
        return null;
    }

    @Override
    public List<String> getAllCategories() {
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
