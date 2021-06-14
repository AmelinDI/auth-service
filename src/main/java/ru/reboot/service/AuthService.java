package ru.reboot.service;

import ru.reboot.dto.User;

import java.util.Collection;
import java.util.List;

public interface AuthService {

    /**
     * Получить информацию о пользователе по userId
     *
     * @throws ru.reboot.error.BusinessLogicException with code USER_NOT_FOUND if user doesn't exist
     * @throws ru.reboot.error.BusinessLogicException with code ILLEGAL_ARGUMENT if userId is empty or null
     */
    User getUserByUserId(String userId);

    /**
     * Получить информацию о пользователе по login
     *
     * @throws ru.reboot.error.BusinessLogicException with code USER_NOT_FOUND if user doesn't exist
     * @throws ru.reboot.error.BusinessLogicException with code ILLEGAL_ARGUMENT if login is empty or null
     */
    User getUserByLogin(String login);

    /**
     * Удалить информацию о пользователе
     *
     * @throws ru.reboot.error.BusinessLogicException with code USER_NOT_FOUND if user doesn't exist
     * @throws ru.reboot.error.BusinessLogicException with code ILLEGAL_ARGUMENT if userId is empty or null
     */
    void deleteUser(String userId);

    /**
     * Создать нового пользователя
     *
     * @throws ru.reboot.error.BusinessLogicException with code USER_ALREADY_EXISTS if user with id exists
     * @throws ru.reboot.error.BusinessLogicException with code DUPLICATE_LOGIN if user with login exists
     * @throws ru.reboot.error.BusinessLogicException with code ILLEGAL_ARGUMENT if request contains wrong data
     */
    User createUser(User user);

    /**
     * Обновить информацию о существующем пользователе
     *
     * @throws ru.reboot.error.BusinessLogicException with code USER_NOT_FOUND if user doesn't exist
     * @throws ru.reboot.error.BusinessLogicException with code DUPLICATE_LOGIN if user with login exists
     * @throws ru.reboot.error.BusinessLogicException with code ILLEGAL_ARGUMENT if request contains wrong data
     */
    User updateUser(User user);

    /**
     * Получить всех пользователей
     */
    List<User> getAllUsers();

    /**
     * Получить всех пользователей по данным ролям
     *
     * @throws ru.reboot.error.BusinessLogicException with code ILLEGAL_ARGUMENT if roles is null
     */
    List<User> getAllUsersByRole(Collection<String> roles);
}
