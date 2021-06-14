package ru.reboot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.reboot.dao.AuthRepositoryImpl;
import ru.reboot.dto.User;
import ru.reboot.error.BusinessLogicException;
import ru.reboot.error.ErrorCodes;
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

    @Test()
    public void negativeUserNoLogin(){
        User user = new User.Builder().setUserID("1").setPassword("pass").build();
        try{
            authService.updateUser(user);
            Assert.fail();
        }
        catch (BusinessLogicException exception){
            Assert.assertEquals(exception.getCode(), ErrorCodes.ILLEGAL_ARGUMENT.name());
        }
    }
    @Test()
    public void negativeUserNoPassword(){
        User user = new User.Builder().setLogin("login").setUserID("1").build();
        try{
            authService.updateUser(user);
            Assert.fail();
        }
        catch (BusinessLogicException exception){
            Assert.assertEquals(exception.getCode(), ErrorCodes.ILLEGAL_ARGUMENT.name());
        }
    }
    @Test()
    public void negativeUserWithLoginNotExists(){
        User user = new User.Builder().setLogin("login").setUserID("1").setPassword("pass").build();
        Mockito.when(authRepository.findUserByLogin("login")).thenReturn(null);
        try{
            authService.updateUser(user);
            Assert.fail();
        }
        catch (BusinessLogicException exception){
            Assert.assertEquals(exception.getCode(), ErrorCodes.USER_NOT_FOUND.name());
        }
    }

    @Test()
    public void positiveCreateUser(){
        User user = new User.Builder().setLogin("login").setUserID("1").setPassword("pass").build();
        Mockito.when(authRepository.findUserByLogin("login")).thenReturn(user);
        Assert.assertEquals(user,authService.updateUser(user));


    }
}
