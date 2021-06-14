package ru.reboot.dao;

import ru.reboot.dto.User;

import java.sql.SQLException;
import java.util.List;

public interface AuthRepository {

    /**
     * Получить информацию о пользователе
     */
    User findUserByUserId(String userId) throws SQLException;

    /**
     * Получить информацию о пользователе
     */
    User findUserByLogin(String login) throws SQLException;

    /**
     * Удалить информацию о товаре
     */
    void deleteUserId(String userId);

    /**
     * Создать нового пользователя
     */
    User createUser(User user);

    /**
     * Обновить информацию о существующем пользователе
     */
    User updateUser(User user);

    /**
     * Получить всех пользователей
     */
    List<User> getAllUsers();
}
