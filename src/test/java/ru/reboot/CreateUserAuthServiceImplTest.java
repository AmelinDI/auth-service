package ru.reboot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.reboot.dao.AuthRepositoryImpl;
import ru.reboot.error.BusinessLogicException;
import ru.reboot.error.ErrorCodes;
import ru.reboot.service.AuthServiceImpl;
import ru.reboot.dto.User;

public class CreateUserAuthServiceImplTest {

    private AuthServiceImpl authService;
    private AuthRepositoryImpl authRepository;



    @Before
    public void Init(){
        authRepository = Mockito.mock(AuthRepositoryImpl.class);
        authService = Mockito.spy(new AuthServiceImpl());
        authService.setAuthRepository(authRepository);
    }


    @Test
    public void negativeUserNoLogin(){
        User user = new User.Builder().setUserID("1").setPassword("pass").build();
        try{
            authService.createUser(user);
        }
        catch (BusinessLogicException exception){
            Assert.assertEquals(exception.getCode(),ErrorCodes.ILLEGAL_ARGUMENT.name());
        }
    }

    @Test
    public void negativeUserNoUserid(){
        User user = new User.Builder().setLogin("login").setPassword("pass").build();
        try{
            authService.createUser(user);
        }
        catch (BusinessLogicException exception){
            Assert.assertEquals(exception.getCode(),ErrorCodes.ILLEGAL_ARGUMENT.name());
        }
    }

    @Test
    public void negativeUserNoPassword(){
        User user = new User.Builder().setLogin("login").setUserID("1").build();
        try{
            authService.createUser(user);
        }
        catch (BusinessLogicException exception){
            Assert.assertEquals(exception.getCode(),ErrorCodes.ILLEGAL_ARGUMENT.name());
        }
    }

    @Test()
    public void negativeUserWithLoginExists(){
        User user = new User.Builder().setLogin("login").setUserID("1").setPassword("pass").build();
        Mockito.when(authRepository.findUserByLogin("login")).thenReturn(user);
        Mockito.when(authRepository.findUserByUserId("login")).thenReturn(null);
        try{
            authService.createUser(user);
        }
        catch (BusinessLogicException exception){
            Assert.assertEquals(exception.getCode(),ErrorCodes.DUPLICATE_LOGIN.name());
        }
    }
    @Test()
    public void negativeUserWithUseridExists(){
        User user = new User.Builder().setLogin("login").setUserID("1").setPassword("pass").build();
        Mockito.when(authRepository.findUserByLogin("login")).thenReturn(null);
        Mockito.when(authRepository.findUserByUserId("1")).thenReturn(user);
        try{
            authService.createUser(user);
        }
        catch (BusinessLogicException exception){
            Assert.assertEquals(exception.getCode(),ErrorCodes.DUPLICATE_USERID.name());
        }
    }

    @Test()
    public void positiveCreateUser(){
        User user = new User.Builder().setLogin("login").setUserID("1").setPassword("pass").build();
        Mockito.when(authRepository.findUserByLogin("login")).thenReturn(null);
        Mockito.when(authRepository.findUserByUserId("1")).thenReturn(null);
        Assert.assertEquals(user,authService.createUser(user));
    }


}
