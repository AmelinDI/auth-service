package ru.reboot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.reboot.dao.AuthRepositoryImpl;
import ru.reboot.error.BusinessLogicException;
import ru.reboot.service.AuthServiceImpl;
import ru.reboot.dto.User;
import ru.reboot.service.Errors;


import java.sql.SQLException;

public class CreateUserAuthServiceImplTest {

    private AuthServiceImpl authService;
    private AuthRepositoryImpl authRepository;



    @Before
    public void Init(){
        authRepository = Mockito.mock(AuthRepositoryImpl.class);
        authService = Mockito.spy(new AuthServiceImpl());
        authService.setAuthRepository(authRepository);
    }


    @Test(expected = BusinessLogicException.class)
    public void negativeUserNoLogin(){
        User user = new User.Builder().setUserID("1").setPassword("pass").build();
        authService.createUser(user);
    }
    @Test(expected = BusinessLogicException.class)
    public void negativeUserNoUserid(){
        User user = new User.Builder().setLogin("login").setPassword("pass").build();
        authService.createUser(user);
    }
    @Test(expected = BusinessLogicException.class)
    public void negativeUserNoPassword(){
        User user = new User.Builder().setLogin("login").setUserID("1").build();
        authService.createUser(user);
    }

    @Test(expected = BusinessLogicException.class)
    public void negativeUserWithLoginExists(){
        User user = new User.Builder().setLogin("login").setUserID("1").setPassword("pass").build();
        Mockito.when(authService.getUserByLogin("login")).thenReturn(user);
        authService.createUser(user);
    }
    @Test(expected = BusinessLogicException.class)
    public void negativeUserWithUseridExists(){
        User user = new User.Builder().setLogin("login").setUserID("1").setPassword("pass").build();
        Mockito.when(authService.getUserByUserId("1")).thenReturn(user);
        authService.createUser(user);
    }

    @Test()
    public void positiveCreateUser(){
        User user = new User.Builder().setLogin("login").setUserID("1").setPassword("pass").build();
        Mockito.when(authService.getUserByLogin("login")).thenReturn(null);
        Mockito.when(authService.getUserByUserId("1")).thenReturn(null);
        Assert.assertEquals(user,authService.createUser(user));
    }


}
