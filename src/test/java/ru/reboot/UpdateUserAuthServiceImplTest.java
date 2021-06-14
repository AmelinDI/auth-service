package ru.reboot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.reboot.dao.AuthRepositoryImpl;
import ru.reboot.dto.User;
import ru.reboot.error.BusinessLogicException;
import ru.reboot.service.AuthServiceImpl;

public class UpdateUserAuthServiceImplTest {

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
        authService.updateUser(user);
    }
    @Test(expected = BusinessLogicException.class)
    public void negativeUserNoPassword(){
        User user = new User.Builder().setLogin("login").setUserID("1").build();
        authService.updateUser(user);
    }
    @Test(expected = BusinessLogicException.class)
    public void negativeUserWithLoginNotExists(){
        User user = new User.Builder().setLogin("login").setUserID("1").setPassword("pass").build();
        Mockito.when(authService.getUserByLogin("login")).thenReturn(null);
        authService.updateUser(user);
    }

    @Test()
    public void positiveCreateUser(){
        User user = new User.Builder().setLogin("login").setUserID("1").setPassword("pass").build();
        Mockito.when(authService.getUserByLogin("login")).thenReturn(user);
        Assert.assertEquals(user,authService.updateUser(user));
    }
}
