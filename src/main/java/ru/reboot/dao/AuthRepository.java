package ru.reboot.dao;

import ru.reboot.dto.User;

import java.util.List;

public interface AuthRepository {

    /**
     * Получить информацию о товаре
     */
    User findItem(String itemId);

    /**
     * Получить товары в определенной категории
     */
    User findAllItemsByCategory(String category);

    /**
     * Удалить информацию о товаре
     */
    void deleteItem(String itemId);

    /**
     * Создать новый товар
     */
    User createItem(User user);

    /**
     * Обновить информацию о существующем товаре
     */
    User updateItem(User user);

    /**
     * Получить все товары
     */
    List<User> getAllItems();

    /**
     * Получить все категории товаров
     */
    List<String> getAllCategories();
}
