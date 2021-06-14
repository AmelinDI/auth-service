package ru.reboot.dao;

import ru.reboot.dto.User;

import java.util.List;

public interface AuthRepository {

    /**
     * Получить информацию о пользователе
     */
    User findUserByUserId(String userId);

    /**
     * Получить информацию о пользователе
     */
    User findUserByLogin(String login);

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
