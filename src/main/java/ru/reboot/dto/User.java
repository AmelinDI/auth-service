package ru.reboot.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class User {

    private String userId;
    private String firstName;
    private String lastName;
    private String secondName;
    private LocalDate birthDate;
    private String login;
    private String password;
    private String role;

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", birthDate=" + birthDate +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public static User userBuilder(ResultSet resultSet) {
        User user = null;
        try {
            user = new User();
            user.userId = resultSet.getString("user_id");
            user.firstName = resultSet.getString("first_name");
            user.lastName = resultSet.getString("last_name");
            user.secondName = resultSet.getString("second_name");
            user.birthDate = resultSet.getDate("birth_date").toLocalDate();
            user.login = resultSet.getString("login");
            user.password = resultSet.getString("password");
            user.role =  resultSet.getString("role");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }
}