package ru.reboot.service;

import org.junit.Test;
import org.mockito.Mockito;
import ru.reboot.dao.AuthRepository;
import ru.reboot.dto.User;
import ru.reboot.error.BusinessLogicException;

import static org.junit.Assert.*;

public class GetUserByAuthServiceImplTest {

    @Test
    public void getUserByUserId() {
        // prepare
        AuthRepository authRepository = Mockito.mock(AuthRepository.class);
        User user1 = new User.Builder()
                .setUserID("1001")
                .setFirstName("Name01")
                .setLogin("login01")
                .setPassword("password01")
                .setRoles("admin")
                .build();
        Mockito.when(authRepository.findUserByUserId("1001")).thenReturn(user1);
        Mockito.when(authRepository.findUserByUserId("50")).thenReturn(null);
        Mockito.when(authRepository.findUserByUserId("five"))
                .thenThrow(new BusinessLogicException("Mockito test exception","ILLEGAL_ARGUMENT"));

        // act
        AuthServiceImpl authService = new AuthServiceImpl();
        authService.setAuthRepository(authRepository);

        // verify
        assertEquals(user1,authService.getUserByUserId("1001"));

        // verify getUserByUserId("50") for Exception
        try {
            authService.getUserByUserId("50");
            fail("getUserByUserId(\"50\") DON'T throws exception");
        } catch (BusinessLogicException ignored) {
        }

        // verify getUserByUserId("five") for Exception
        try {
            authService.getUserByUserId("five");
            fail("getUserByUserId(\"five\") DON'T throws exception");
        } catch (BusinessLogicException ignored) {
        }
    }

    @Test
    public void getUserByLogin() {
        // prepare
        AuthRepository authRepository = Mockito.mock(AuthRepository.class);
        User user2 = new User.Builder()
                .setUserID("1002")
                .setFirstName("Name02")
                .setLogin("login02")
                .setPassword("password02")
                .setRoles("admin")
                .build();
        Mockito.when(authRepository.findUserByLogin("login02")).thenReturn(user2);
        Mockito.when(authRepository.findUserByLogin("loginForNull")).thenReturn(null);
        Mockito.when(authRepository.findUserByLogin("loginForException"))
                .thenThrow(new BusinessLogicException("Mockito test exception","ILLEGAL_ARGUMENT"));

        // act
        AuthServiceImpl authService = new AuthServiceImpl();
        authService.setAuthRepository(authRepository);

        // verify
        assertEquals(user2,authService.getUserByLogin("login02"));

        // verify getUserByLogin("loginForNull") for Exception
        try {
            authService.getUserByLogin("loginForNull");
            fail("getUserByLogin(\"loginForNull\") DON'T throws exception");
        } catch (BusinessLogicException ignored) {
        }

        // verify getUserByLogin("loginForException") for Exception
        try {
            authService.getUserByLogin("loginForException");
            fail("getUserByLogin(\"loginForException\") DON'T throws exception");
        } catch (BusinessLogicException ignored) {
        }
    }
}