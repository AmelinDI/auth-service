package ru.reboot.controller;

import ru.reboot.dto.User;

import java.util.List;

public interface AuthController {

    /**
     * Получить информацию о товаре
     */
    User getItem(String itemId);

    /**
     * Получить товары в определенной категории
     */
    User getAllItemsByCategory(String category);

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

    /**
     * Забронировать определенное количество товара
     */
    User reserveItem(String itemId, int count);

    /**
     * Отменить бронь определенного количества товара
     */
    User unreserveItem(String itemId, int count);
}
