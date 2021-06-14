package ru.reboot.service;

import ch.qos.logback.classic.spi.IThrowableProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.reboot.dao.AuthRepository;
import ru.reboot.dto.User;
import ru.reboot.error.BusinessLogicException;
import ru.reboot.error.ErrorCodes;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Component
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

    @Override
    public void deleteUser(String userId) {

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
        if(!Objects.isNull(getUserByLogin(user.getLogin()))){
            throw new BusinessLogicException("User with that login already exists",ErrorCodes.DUPLICATE_LOGIN.name());
        }
        if(!Objects.isNull(getUserByUserId(user.getUserId()))){
            throw new BusinessLogicException("User with that userid already exists",ErrorCodes.DUPLICATE_USERID.name());
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
        if(Objects.isNull(getUserByLogin(user.getLogin()))){
            throw new BusinessLogicException("User with that login not exists",ErrorCodes.USER_NOT_FOUND.name());
        }
        authRepository.updateUser(user);
        logger.info("Method .createUser completed inputParam_1={}, result={}",user,user);
        return user;
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
