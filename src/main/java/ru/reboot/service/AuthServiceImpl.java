package ru.reboot.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.reboot.dao.AuthRepository;
import ru.reboot.dto.User;
import ru.reboot.error.BusinessLogicException;

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

    /**
     * Returns registered user from database with specified user_id
     * or throws BusinessLogicException
     *
     * @param userId - user id
     * @return User
     */
    @Override
    public User getUserByUserId(String userId) {
        User result;
        logger.info("Method .getUserByLogin userId={}",userId);

        result = authRepository.findUserByUserId(userId);
        if (result == null) {
            throw new BusinessLogicException("User with userId=" + userId + "not found","USER_NOT_FOUND");
        }

        logger.info("Method .getUserByLogin completed userId={} result={}",userId,result);
        return result;
    }

    /**
     * Returns registered user from database with specified login
     * or throws BusinessLogicException
     *
     * @param login - user login
     * @return User
     */
    @Override
    public User getUserByLogin(String login) {
        User result;
        logger.info("Method .getUserByLogin login={}",login);

        result = authRepository.findUserByLogin(login);
        if (result == null) {
            throw new BusinessLogicException("User with login=" + login + "not found","USER_NOT_FOUND");
        }

        logger.info("Method .getUserByLogin completed login={} result={}",login,result);
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
