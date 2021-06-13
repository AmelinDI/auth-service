package ru.reboot.dao;

import org.springframework.stereotype.Repository;
import ru.reboot.dto.User;
import ru.reboot.error.BusinessLogicException;

import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Auth repository.
 */
@Repository
public class AuthRepositoryImpl implements AuthRepository {

    private final Connection connection;

    public AuthRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User findUserByUserId(String userId) {
        return null;
    }

    @Override
    public User findUserByLogin(String login) {
        return null;
    }

    /**
     * Method delete user by id that gets in params
     * @param userId
     */
    @Override
    public void deleteUserId(String userId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE USER_ID  = ?")) {
            preparedStatement.setString(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new BusinessLogicException("Sql Exception have been throw in method deleteUserId", "deleteUserId");
        }
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    /**
     * Method return all users got from repository
     * @return  List<User> or throw BusinessLogicException
     */
    @Override
    public List<User> getAllUsers() {
        List<User> allUsersInBase = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getString("user_id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setSecondName(resultSet.getString("second_name"));
                user.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
                allUsersInBase.add(user);
            }
            return allUsersInBase;
        } catch (SQLException e) {
            throw new BusinessLogicException("Sql Exception have been throw in method getAllUsers", e.getMessage());
        }
    }

    @PreDestroy
    public void onClose() {
        try {
            connection.close();
        } catch (Exception ex) {
        }
    }
}
