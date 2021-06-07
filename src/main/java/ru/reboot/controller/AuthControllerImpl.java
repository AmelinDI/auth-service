package ru.reboot.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.reboot.dao.AuthRepositoryImpl;
import ru.reboot.dto.User;
import ru.reboot.service.AuthService;

import java.util.Date;
import java.util.List;

/**
 * User controller.
 */
@RestController
@RequestMapping(path = "auth")
public class AuthControllerImpl implements AuthController {

    private static final Logger logger = LogManager.getLogger(AuthRepositoryImpl.class);

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
    public User getItem(String itemId) {
        return authService.getItem(itemId);
    }

    @Override
    public User getAllItemsByCategory(String category) {
        return authService.getAllItemsByCategory(category);
    }

    @Override
    public void deleteItem(String itemId) {
        authService.deleteItem(itemId);
    }

    @Override
    public User createItem(User user) {
        return authService.createItem(user);
    }

    @Override
    public User updateItem(User user) {
        return authService.updateItem(user);
    }

    @Override
    public List<User> getAllItems() {
        return authService.getAllItems();
    }

    @Override
    public List<String> getAllCategories() {
        return authService.getAllCategories();
    }

    @Override
    public User reserveItem(String itemId, int count) {
        return authService.reserveItem(itemId, count);
    }

    @Override
    public User unreserveItem(String itemId, int count) {
        return authService.unreserveItem(itemId, count);
    }
}
