package ru.reboot.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.reboot.dao.AuthRepository;
import ru.reboot.dto.User;
import ru.reboot.error.BusinessLogicException;
import ru.reboot.error.ErrorCodes;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
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
        try {
            logger.info("Method .getUserByUserId userId={}", userId);

            result = authRepository.findUserByUserId(userId);
            if (result == null) {
                throw new BusinessLogicException("User with userId=" + userId + "not found", ErrorCodes.USER_NOT_FOUND);
            }

            logger.info("Method .getUserByUserId completed userId={} result={}", userId, result);
            return result;
        } catch (Exception exp) {
            logger.error("Method .getUserByUserId userId={} error={}", userId, exp.toString(), exp);
            throw exp;
        }

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
        try {
            logger.info("Method .getUserByLogin login={}", login);

            result = authRepository.findUserByLogin(login);
            if (result == null) {
                throw new BusinessLogicException("User with login=" + login + "not found", ErrorCodes.USER_NOT_FOUND);
            }

            logger.info("Method .getUserByLogin completed login={} result={}", login, result);
            return result;
        } catch (Exception exp) {
            logger.error("Method .getUserByLogin login={} error={}", login, exp.toString(), exp);
            throw exp;
        }

    }

    /**
     * Method delete user by id that gets in params
     *
     * @param userId - userId of User to delete
     */
    @Override
    public void deleteUser(String userId) {
        try {
            logger.info("Method .deleteUser userId={}.", userId);
            if (userId == null || userId.isEmpty()) {
                throw new BusinessLogicException("UserId is empty or null", ErrorCodes.ILLEGAL_ARGUMENT);
            }
            Optional.ofNullable(authRepository.findUserByUserId(userId)).orElseThrow(() -> new BusinessLogicException("user doesn't exist", ErrorCodes.USER_NOT_FOUND));
            authRepository.deleteUserId(userId);
            logger.info("Method .deleteUser completed");
        } catch (Exception exp) {
            logger.error("Method .deleteUser userId={} error={}", userId, exp.toString(), exp);
            throw exp;
        }

    }

    /**
     * Service method of creating user
     *
     * @param user - user,that needs to be created in DB
     * @return Passed user if success creating or throw exception BusinessLogicException.class if USER_NOT_CONSISTENT or USER_ALREADY_EXISTS
     */
    @Override
    public User createUser(User user) {
        try {
            logger.info("Method .createUser User={}", user);
            if (Objects.isNull(user.getLogin()) || Objects.isNull(user.getPassword()) || Objects.isNull(user.getUserId())) {
                throw new BusinessLogicException("User doesnt have login,password or userid", ErrorCodes.ILLEGAL_ARGUMENT);
            }
            if (!Objects.isNull(authRepository.findUserByLogin(user.getLogin()))) {
                throw new BusinessLogicException("User with login= " + user.getLogin() + " already exists", ErrorCodes.ILLEGAL_ARGUMENT);
            }
            if (!Objects.isNull(authRepository.findUserByUserId(user.getUserId()))) {
                throw new BusinessLogicException("User with userid= " + user.getUserId() + " already exists", ErrorCodes.ILLEGAL_ARGUMENT);
            }
            authRepository.createUser(user);
            logger.info("Method .createUser completed inputParam_1={}, result={}", user, user);
            return user;
        } catch (Exception exp) {
            logger.error("Method .createUser User={} error={}", user, exp.toString(), exp);
            throw exp;
        }

    }

    /**
     * Service method of updating user
     *
     * @param user - user,that needs to be updated in DB
     * @return Passed user if success updating or throw exception BusinessLogicException.class if USER_NOT_CONSISTENT or USER_NOT_EXISTS
     */
    @Override
    public User updateUser(User user) {
        try {
            logger.info("Method .updateUser User={}", user);
            if (Objects.isNull(user.getLogin()) || Objects.isNull(user.getPassword())) {
                throw new BusinessLogicException("User doesnt have login or password", ErrorCodes.ILLEGAL_ARGUMENT);
            }
            if (Objects.isNull(authRepository.findUserByLogin(user.getLogin()))) {
                throw new BusinessLogicException("User with login= " + user.getLogin() + " not exists", ErrorCodes.USER_NOT_FOUND);
            }
            authRepository.updateUser(user);
            logger.info("Method .updateUser completed User={}, result={}", user, user);
            return user;
        } catch (Exception exp) {
            logger.error("Method .updateUser User={} error={}", user, exp.toString(), exp);
            throw exp;
        }

    }

    /**
     * Method gets all user from repository and return list of users
     *
     * @return List<User>
     */
    @Override
    public List<User> getAllUsers() {
        try {
            logger.info("Method .getAllUsers");
            List<User> users = authRepository.getAllUsers();
            logger.info("Method .getAllUsers completed result = {}", users);
            return users;
        } catch (Exception exp) {
            logger.error("Method .getAllUsers error={}", exp.toString(), exp);
            throw exp;
        }
    }

    /**
     * Method gets collection of roles in params and gets all users from repository and return user list only have a role contained in roles collection
     *
     * @param roles - Collection of roles
     * @return List<User>
     */
    @Override
    public List<User> getAllUsersByRole(Collection<String> roles) {
        try {
            logger.info("Method .getAllUsersByRole roles={}", roles);
            if (roles == null || roles.isEmpty())
                throw new BusinessLogicException("Collection of roles is empty or null", ErrorCodes.ILLEGAL_ARGUMENT);
            else {
                List<User> users = authRepository.getAllUsers()
                        .stream()
                        .filter(user -> roles.contains(user.getRole()))
                        .collect(Collectors.toList());

                logger.info("Method .getAllUsersByRole completed  roles={}, result={}", roles, users);
                return users;
            }
        } catch (Exception exp) {
            logger.error("Method .getAllUsersByRole Roles={} error={}", roles, exp.toString(), exp);
            throw exp;
        }

    }
}
