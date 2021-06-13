package ru.reboot.service;

import ru.reboot.dto.User;

import java.util.Collection;
import java.util.List;

public interface AuthService {

    /**
     * Получить информацию о пользователе по userId
     */
    User getUserByUserId(String userId);

    /**
     * Получить информацию о пользователе по login
     */
    User getUserByLogin(String login);

    /**
     * Удалить информацию о пользователе
     */
    void deleteUser(String userId);

    /**
     * Создать нового пользователя
     */
    User createUser(User user);

    /**
     * Обновить информацию о существующем пользователе
     */
    User updateUser(User user);

    /**
     * Получить все товары
     */
    List<User> getAllUsers();

    /**
     * Получить всех пользователей по данным ролям
     * @return
     */
    List<String> getAllUsersByRole(Collection<String> roles);
}
