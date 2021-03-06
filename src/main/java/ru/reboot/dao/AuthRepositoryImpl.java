package ru.reboot.dao;

import ru.reboot.dto.User;
import ru.reboot.error.BusinessLogicException;
import ru.reboot.error.ErrorCodes;

import javax.annotation.PreDestroy;
import java.sql.*;
import java.util.ArrayList;
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
            ps.setString(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = new User.Builder()
                            .setUserID(rs.getString("user_id"))
                            .setFirstName(rs.getString("first_name"))
                            .setLastName(rs.getString("last_name"))
                            .setSecondName(rs.getString("second_name"))
                            .setBirthDate(rs.getDate("birth_date")==null ? null: rs.getDate("birth_date").toLocalDate())
                            .setLogin(rs.getString("login"))
                            .setPassword(rs.getString("password"))
                            .setRoles(rs.getString("roles"))
                            .build();
                }
            }
        } catch (SQLException e) {
            throw new BusinessLogicException("Exception in DB: " + e.getMessage(), ErrorCodes.DATABASE_ERROR);
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
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = new User.Builder()
                            .setUserID(rs.getString("user_id"))
                            .setFirstName(rs.getString("first_name"))
                            .setLastName(rs.getString("last_name"))
                            .setSecondName(rs.getString("second_name"))
                            .setBirthDate(rs.getDate("birth_date")==null ? null: rs.getDate("birth_date").toLocalDate())
                            .setLogin(rs.getString("login"))
                            .setPassword(rs.getString("password"))
                            .setRoles(rs.getString("roles"))
                            .build();
                }
            }
        } catch (SQLException e) {
            throw new BusinessLogicException("Exception in DB: " + e.getMessage(), ErrorCodes.DATABASE_ERROR);
        }
        return result;
    }

    /**
     * Method delete user by id that gets in params
     *
     * @param userId - userId of User to delete
     */
    @Override
    public void deleteUserId(String userId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE USER_ID  = ?")) {
            preparedStatement.setString(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new BusinessLogicException("Sql Exception have been throw in method deleteUserId", ErrorCodes.DATABASE_ERROR);
        }
    }


    /**
     * Dao method of creating a new user in DB
     *
     * @param user - what user needs to be created
     * @return Passed user if success creating or throw exception BusinessLogicException.class if PreparedStatement.executeUpdate is failed
     */
    @Override
    public User createUser(User user) {
        List<String> roles = user.getRoles();
        String stringRoles;
        if ( roles != null) {
            stringRoles = String.join(",", roles);
            if (stringRoles.equals("")) {
                stringRoles=null;
            }
        } else {
            stringRoles = null;
        }

        String query = "INSERT INTO users (user_id,first_name,last_name,second_name,birth_date,login,password,roles) Values (?,?,?,?,?,?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,user.getUserId());
            preparedStatement.setString(2,user.getFirstName());
            preparedStatement.setString(3,user.getLastName());
            preparedStatement.setString(4, user.getSecondName());
            preparedStatement.setDate(5, user.getBirthDate()==null ? null : Date.valueOf(user.getBirthDate()));
            preparedStatement.setString(6, user.getLogin());
            preparedStatement.setString(7, user.getPassword());
            preparedStatement.setString(8, stringRoles);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new BusinessLogicException(exception.getMessage(), ErrorCodes.DATABASE_ERROR);
        }
        return user;
    }

    /**
     * DAO method of updating user data
     *
     * @param user - what user needs to be updated
     * @return Passed user if success updating or throw exception BusinessLogicException.class if PreparedStatement.executeUpdate is failed
     */
    @Override
    public User updateUser(User user) {
        List<String> roles = user.getRoles();
        String stringRoles;
        if ( roles != null) {
            stringRoles = String.join(",", roles);
            if (stringRoles.equals("")) {
                stringRoles=null;
            }
        } else {
            stringRoles = null;
        }

        String query = "UPDATE users SET first_name = ?, last_name = ?, second_name = ?, birth_date = ?, password = ?, roles = ? where login = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,user.getFirstName());
            preparedStatement.setString(2,user.getLastName());
            preparedStatement.setString(3, user.getSecondName());
            preparedStatement.setDate(4, user.getBirthDate()==null ? null : Date.valueOf(user.getBirthDate()));
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, stringRoles);
            preparedStatement.setString(7,user.getLogin());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new BusinessLogicException(exception.getMessage(), ErrorCodes.DATABASE_ERROR);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsersInBase = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User.Builder()
                        .setUserID(resultSet.getString("user_id"))
                        .setFirstName(resultSet.getString("first_name"))
                        .setLastName(resultSet.getString("last_name"))
                        .setSecondName(resultSet.getString("second_name"))
                        .setBirthDate(resultSet.getDate("birth_date")==null ? null: resultSet.getDate("birth_date").toLocalDate())
                        .setLogin(resultSet.getString("login"))
                        .setPassword(resultSet.getString("password"))
                        .setRoles(resultSet.getString("roles"))
                        .build();
                allUsersInBase.add(user);
            }
            return allUsersInBase;
        } catch (SQLException e) {
            throw new BusinessLogicException(e.getMessage(), ErrorCodes.DATABASE_ERROR);
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
