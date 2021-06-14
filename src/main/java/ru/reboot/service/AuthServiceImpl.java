package ru.reboot.service;

import ch.qos.logback.classic.spi.IThrowableProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.reboot.dao.AuthRepository;
import ru.reboot.dto.User;
import ru.reboot.error.BusinessLogicException;
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

    /**
     * Method delete user by id that gets in params
     *
     * @param userId
     */
    @Override
    public void deleteUser(String userId) {
        logger.info("Method .deleteUser userId={}.", userId);
        if (userId == null || userId.isEmpty()) {
            throw new BusinessLogicException("UserId is empty or null", ErrorCodes.ILLEGAL_ARGUMENT.name());
        }
        Optional.ofNullable(authRepository.findUserByUserId(userId)).orElseThrow(() -> new BusinessLogicException("user doesn't exist", ErrorCodes.USER_NOT_FOUND.name()));
        authRepository.deleteUserId(userId);
        logger.info("Method .deleteUser completed");
    }

    /**
     * Service method of creating user
     * @param user - user,that needs to be created in DB
     * @return Passed user if success creating or throw exception BusinessLogicException.class if USER_NOT_CONSISTENT or USER_ALREADY_EXISTS
     */
    @Override
    public User createUser(User user) {
        logger.info("Method .createUser inputParam_1={}",user);
        if (Objects.isNull(user.getLogin()) || Objects.isNull(user.getPassword()) || Objects.isNull(user.getUserId())){
            throw new BusinessLogicException("User doesnt have login,password or userid", ErrorCodes.ILLEGAL_ARGUMENT.name());
        }
        if(!Objects.isNull(authRepository.findUserByLogin(user.getLogin()))){
            throw new BusinessLogicException("User with login= " + user.getLogin() + " already exists",ErrorCodes.DUPLICATE_LOGIN.name());
        }
        if(!Objects.isNull(authRepository.findUserByUserId(user.getUserId()))){
            throw new BusinessLogicException("User with userid= " +user.getUserId() + " already exists",ErrorCodes.DUPLICATE_USERID.name());
        }
        authRepository.createUser(user);
        logger.info("Method .createUser completed inputParam_1={}, result={}",user,user);
        return user;
    }

    /**
     * Service method of updating user
     * @param user - user,that needs to be updated in DB
     * @return Passed user if success updating or throw exception BusinessLogicException.class if USER_NOT_CONSISTENT or USER_NOT_EXISTS
     */
    @Override
    public User updateUser(User user) {
        logger.info("Method .updateUser inputParam_1={}",user);
        if (Objects.isNull(user.getLogin()) || Objects.isNull(user.getPassword())){
            throw new BusinessLogicException("User doesnt have login or password", ErrorCodes.ILLEGAL_ARGUMENT.name());
        }
        if(Objects.isNull(authRepository.findUserByLogin(user.getLogin()))){
            throw new BusinessLogicException("User with login= " +user.getLogin() + " not exists",ErrorCodes.USER_NOT_FOUND.name());
        }
        authRepository.updateUser(user);
        logger.info("Method .createUser completed inputParam_1={}, result={}",user,user);
        return user;
    }

    /**
     * Method gets all user from repository and return list of users
     *
     * @return List<User>
     */
    @Override
    public List<User> getAllUsers() {
        logger.info("Method .getAllUsers");
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
        logger.info("Method .getAllUsersByRole roles={}", roles);
        if (roles == null || roles.isEmpty())
            throw new BusinessLogicException("Collection role is empty or null", ErrorCodes.ILLEGAL_ARGUMENT.name());
        else {
            List<User> users = authRepository.getAllUsers()
                    .stream()
                    .filter(user -> roles.contains(user.getRole()))
                    .collect(Collectors.toList());

            logger.info("Method .getAllUsersByRole completed  roles={}, result={}", roles, users);
            return users;
        }
    }
}
