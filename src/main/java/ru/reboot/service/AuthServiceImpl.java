package ru.reboot.service;

import ch.qos.logback.classic.spi.IThrowableProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.reboot.dao.AuthRepository;
import ru.reboot.dto.User;
import ru.reboot.error.BusinessLogicException;

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
        logger.info("Method .createUser param1={}",user);
        if (Objects.isNull(user.getLogin()) || Objects.isNull(user.getPassword()) || Objects.isNull(user.getUserId())){
            throw new BusinessLogicException("User doesnt have login,password or userid", Errors.USER_NOT_CONSISTENT.name());
        }
        if(!Objects.isNull(getUserByLogin(user.getLogin())) || !Objects.isNull(getUserByUserId(user.getUserId()))){
            throw new BusinessLogicException("User with that login or userid already exists",Errors.USER_ALREADY_EXISTS.name());
        }
        authRepository.createUser(user);
        logger.info("Method .createUser completed param1={}, result={}",user,user);
        return user;
    }

    /**
     * Service method of updating user
     * @param user - user,that needs to be updated in DB
     * @return Passed user if success updating or throw exception BusinessLogicException.class if USER_NOT_CONSISTENT or USER_NOT_EXISTS
     */
    @Override
    public User updateUser(User user) {
        logger.info("Method .updateUser param1={}",user);
        if (Objects.isNull(user.getLogin()) || Objects.isNull(user.getPassword())){
            throw new BusinessLogicException("User doesnt have login or password", Errors.USER_NOT_CONSISTENT.name());
        }
        if(Objects.isNull(getUserByLogin(user.getLogin()))){
            throw new BusinessLogicException("User with that login not exists",Errors.USER_NOT_EXISTS.name());
        }
        authRepository.updateUser(user);
        logger.info("Method .createUser completed param1={}, result={}",user,user);
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
