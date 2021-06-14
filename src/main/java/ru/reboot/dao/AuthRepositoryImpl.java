package ru.reboot.dao;

import ru.reboot.dto.User;
import ru.reboot.error.BusinessExceptionCode;
import ru.reboot.error.BusinessLogicException;

import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Auth repository.
 */
public class AuthRepositoryImpl implements AuthRepository {

    private final Connection connection;

    public AuthRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Returns registered user from database with specified userId
     * or null if user does not exist
     *
     * @param userId - user id
     * @return User or null
     */
    @Override
    public User findUserByUserId(String userId) {
        User result = null;

        try (PreparedStatement ps = connection.prepareStatement("select * from users where user_id = ?")) {
            ps.setString(1,userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = new User.Builder()
                            .setUserID(rs.getString("user_id"))
                            .setFirstName(rs.getString("first_name"))
                            .setLastName(rs.getString("last_name"))
                            .setSecondName(rs.getString("second_name"))
                            .setBirthDate(rs.getTimestamp("birth_date").toLocalDateTime().toLocalDate())
                            .setLogin(rs.getString("login"))
                            .setPassword(rs.getString("password"))
                            .setRole(rs.getString("role"))
                            .build();
                }
            }
        } catch (SQLException e) {
            throw new BusinessLogicException("Exception in DB: " + e.getMessage(),BusinessExceptionCode.DATABASE_ERROR);
        }
        return result;
    }


    /**
     * Returns registered user from database with specified login
     * or null if user does not exist
     *
     * @param login - user login
     * @return User or null
     */
    @Override
    public User findUserByLogin(String login) {
        User result = null;

        try (PreparedStatement ps = connection.prepareStatement("select * from users where login = ?")) {
            ps.setString(1,login);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = new User.Builder()
                            .setUserID(rs.getString("user_id"))
                            .setFirstName(rs.getString("first_name"))
                            .setLastName(rs.getString("last_name"))
                            .setSecondName(rs.getString("second_name"))
                            .setBirthDate(rs.getDate("birth_date").toLocalDate())
                            .setBirthDate(rs.getTimestamp("birth_date").toLocalDateTime().toLocalDate())
                            .setLogin(rs.getString("login"))
                            .setPassword(rs.getString("password"))
                            .setRole(rs.getString("role"))
                            .build();
                }
            }
        } catch (SQLException e) {
            throw new BusinessLogicException("Exception in DB: " + e.getMessage(),BusinessExceptionCode.DATABASE_ERROR);
        }
        return result;
    }

    @Override
    public void deleteUserId(String userId) {

    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
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
