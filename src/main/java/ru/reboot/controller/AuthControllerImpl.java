package ru.reboot.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.reboot.dao.AuthRepositoryImpl;
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
    @GetMapping("/user?userId={userId}")
    public User getUserByUserId(@PathVariable("userId") String userId) {
        return authService.getUserByUserId(userId);
    }

    @Override
    @GetMapping("/user?login={login}")
    public User getUserByLogin(@PathVariable("login") String login) {
        return authService.getUserByLogin(login);
    }

    @Override
    public void deleteUser(String userId) {
        authService.deleteUser(userId);
    }

    @Override
    public User createUser(User user) {
        return authService.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        return authService.updateUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return authService.getAllUsers();
    }

    @Override
    public List<String> getAllUsersByRole(Collection<String> roles) {
        return authService.getAllUsersByRole(roles);
    }
}
