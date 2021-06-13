package ru.reboot.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.reboot.dao.AuthRepository;
import ru.reboot.dao.AuthRepositoryImpl;
import ru.reboot.dto.User;

import java.util.Collection;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LogManager.getLogger(AuthServiceImpl.class);
    private AuthRepository authRepository;

    @Autowired
    public void setAuthRepository(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public User getUserByUserId(String userId) {
        User result;
        logger.info("Method getUserByUserId userId="+userId+" (Заход в метод)");
        result = authRepository.findUserByUserId(userId);
        logger.info("Method getUserByUserId completed userId="+userId+" result="+result);
        return result;
    }

    @Override
    public User getUserByLogin(String login) {
        User result;
        logger.info("Method getUserByLogin login="+login+" (Заход в метод)");
        result = authRepository.findUserByLogin(login);
        logger.info("Method getUserByLogin completed login="+login+" result="+result);
        return result;
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
