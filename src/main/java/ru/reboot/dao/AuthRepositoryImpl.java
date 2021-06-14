package ru.reboot.dao;

import ru.reboot.dto.User;
import ru.reboot.error.BusinessLogicException;
import ru.reboot.error.ErrorCodes;

import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

/**
 * Auth repository.
 */
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

    @Override
    public void deleteUserId(String userId) {

    }


    /**
     * Dao method of creating a new user in DB
     * @param user - what user needs to be created
     * @return Passed user if success creating or throw exception BusinessLogicException.class if PreparedStatement.executeUpdate is failed
     */
    @Override
    public User createUser(User user) {
        String query = "INSERT INTO user (user_id,first_name,last_name,second_name,birth_date,login,password,role) Values (?,?,?,?,?,?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,user.getUserId());
            preparedStatement.setString(2,user.getFirstName());
            preparedStatement.setString(3,user.getLastName());
            preparedStatement.setString(4, user.getSecondName());
            preparedStatement.setDate(5, Date.valueOf(user.getBirthDate()));
            preparedStatement.setString(6, user.getLogin());
            preparedStatement.setString(7, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new BusinessLogicException(exception.getMessage(), ErrorCodes.CANT_CREATE_NEW_USER.name());
        }
        return user;
    }

    /**
     * DAO method of updating user data
     * @param user - what user needs to be updated
     * @return Passed user if success updating or throw exception BusinessLogicException.class if PreparedStatement.executeUpdate is failed
     */
    @Override
    public User updateUser(User user) {
        String query = "UPDATE user where login = ? SET first_name = ?,last_name = ?,second_name = ?,birth_date = ?, password = ?,role = ?) Values (?,?,?,?,?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,user.getLogin());
            preparedStatement.setString(2,user.getFirstName());
            preparedStatement.setString(3,user.getLastName());
            preparedStatement.setString(4, user.getSecondName());
            preparedStatement.setDate(5, Date.valueOf(user.getBirthDate()));
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setString(7, user.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new BusinessLogicException(exception.getMessage(), ErrorCodes.CANT_UPDATE_USER.name());
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @PreDestroy
    public void onClose() {
        try {
            connection.close();
        } catch (Exception ex) {
        }
    }
}
