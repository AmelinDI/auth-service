package ru.reboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.reboot.dao.AuthRepository;
import ru.reboot.dto.User;

import java.util.List;

@Component
public class AuthServiceImpl implements AuthService {

    private AuthRepository authRepository;

    @Autowired
    public void setAuthRepository(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public User getItem(String itemId) {
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

    @Override
    public User reserveItem(String itemId, int count) {
        return null;
    }

    @Override
    public User unreserveItem(String itemId, int count) {
        return null;
    }

    @Override
    public User getAllItemsByCategory(String category) {
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
}
