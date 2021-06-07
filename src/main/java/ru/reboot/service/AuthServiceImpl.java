package ru.reboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.reboot.dao.AuthRepository;
import ru.reboot.dto.User;

import java.util.Collection;
import java.util.List;

@Component
public class AuthServiceImpl implements AuthService {

    private AuthRepository authRepository;

    @Autowired
    public void setAuthRepository(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public User getUserByUserId(String userId) {
        return null;
    }

    @Override
    public User getUserByLogin(String login) {
        return null;
    }

    @Override
    public void deleteUser(String userId) {

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

    @Override
    public List<String> getAllUsersByRole(Collection<String> roles) {
        return null;
    }
}
