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
import java.util.Optional;
import java.util.stream.Collectors;

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
        return null;
    }

    @Override
    public User getUserByLogin(String login) {
        return null;
    }

    /**
     * Method delete user by id that gets in params
     *
     * @param userId
     */
    @Override
    public void deleteUser(String userId) {
        logger.info("Method .deleteUser param1={}.", userId);
        Optional.ofNullable(authRepository.findUserByUserId(userId)).orElseThrow(()->new BusinessLogicException("User not found or id is present in unsupported format", "NoUser"));
        authRepository.deleteUserId(userId);
        logger.info("Method .deleteUser completed  param1={}", userId);
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    /**
     * Method gets all user from repository and return list of users
     *
     * @return List<User>
     */
    @Override
    public List<User> getAllUsers() {
        List<User> users = authRepository.getAllUsers();
        logger.info("Method .getAllUsers completed result = {}", users);
        return users;
    }

    /**
     * Method gets collection of roles in params and gets all users from repository and return user list only have a role contained in roles collection
     *
     * @param roles
     * @return List<User>
     */
    @Override
    public List<User> getAllUsersByRole(Collection<String> roles) {
        logger.info("Method .getAllUsersByRole param1={}", roles);
        if (roles == null || roles.isEmpty())
            throw new BusinessLogicException("Input params is empty or dont contains any roles ", "BadInputParams");
        else {
            List<User> users = authRepository.getAllUsers()
                    .stream()
                    .filter(user -> roles.contains(user.getRole()))
                    .collect(Collectors.toList());

            logger.info("Method .getAllUsersByRole completed  param1={}, result = {}", roles, users);
            return users;
        }
    }
}
