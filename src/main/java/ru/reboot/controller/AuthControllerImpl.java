package ru.reboot.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.reboot.dto.User;
import ru.reboot.service.AuthService;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * User controller.
 */
@RestController
@RequestMapping(path = "auth")
public class AuthControllerImpl implements AuthController {

    private static final Logger logger = LogManager.getLogger(AuthControllerImpl.class);

    private AuthService authService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("info")
    public String info() {
        logger.info("method .info invoked");
        return "AuthController " + new Date();
    }

    @Override
    public User getUserByUserId(String userId) {
        return authService.getUserByUserId(userId);
    }

    @Override
    public User getUserByLogin(String login) {
        return authService.getUserByLogin(login);
    }

    /**
     * Method gets variable from path and delete user by id
     *
     * @param userid
     */
    @Override
    @DeleteMapping("/user/{userid}")
    public void deleteUser(@PathVariable("userid") String userid) {
        authService.deleteUser(userid);
    }

    @Override
    public User createUser(User user) {
        return authService.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        return authService.updateUser(user);
    }

    /**
     * Method gets all user from repository and return list of users
     *
     * @return List<User>
     */
    @Override
    @GetMapping("/user/all")
    public List<User> getAllUsers() {
        return authService.getAllUsers();
    }

    /**
     * Method take a collection of roles in parameters and return list of user with this roles
     *
     * @param roles
     * @return List<User>
     */
    @Override
    @GetMapping("/user/all/byRoles")
    public List<User> getAllUsersByRole(Collection<String> roles) {
        return authService.getAllUsersByRole(roles);
    }
}
