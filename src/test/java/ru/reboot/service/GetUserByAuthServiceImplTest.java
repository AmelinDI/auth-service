package ru.reboot.service;

import org.junit.Test;
import org.mockito.Mockito;
import ru.reboot.dao.AuthRepository;
import ru.reboot.dto.User;
import ru.reboot.error.BusinessLogicException;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class GetUserByAuthServiceImplTest {

    @Test
    public void getUserByUserId() throws SQLException {
        // prepare
        AuthRepository authRepository = Mockito.mock(AuthRepository.class);
        User user1 = new User.Builder()
                .setUserID("1001")
                .setFirstName("Name01")
                .setLogin("login01")
                .setPassword("password01")
                .setRole("admin")
                .build();
        User user3 = new User.Builder()
                .setUserID("1003")
                .setFirstName("Name03")
                .setLogin("login03")
                .setPassword("password03")
                .setRole("admin")
                .build();
        Mockito.when(authRepository.findUserByUserId("1001")).thenReturn(user1);
        Mockito.when(authRepository.findUserByUserId("50")).thenReturn(null);
        Mockito.when(authRepository.findUserByUserId("five")).thenThrow(new SQLException());

        // act
        AuthServiceImpl authService = new AuthServiceImpl();
        authService.setAuthRepository(authRepository);

        // verify
        assertEquals(user1,authService.getUserByUserId("1001"));
        assertThrows(BusinessLogicException.class,() -> authService.getUserByUserId("50"));
        assertThrows(BusinessLogicException.class,() -> authService.getUserByUserId("five"));
    }

    @Test
    public void getUserByLogin() throws SQLException {
        // prepare
        AuthRepository authRepository = Mockito.mock(AuthRepository.class);
        User user2 = new User.Builder()
                .setUserID("1002")
                .setFirstName("Name02")
                .setLogin("login02")
                .setPassword("password02")
                .setRole("admin")
                .build();
        Mockito.when(authRepository.findUserByLogin("login02")).thenReturn(user2);
        Mockito.when(authRepository.findUserByLogin("incorrect")).thenReturn(null);
        Mockito.when(authRepository.findUserByLogin("logooff")).thenThrow(new SQLException());

        // act
        AuthServiceImpl authService = new AuthServiceImpl();
        authService.setAuthRepository(authRepository);

        // verify
        assertEquals(user2,authService.getUserByLogin("login02"));
        assertThrows(BusinessLogicException.class,() -> authService.getUserByLogin("incorrect"));
        assertThrows(BusinessLogicException.class,() -> authService.getUserByLogin("logooff"));
    }
}