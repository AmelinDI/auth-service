package ru.reboot.service;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.reboot.dao.AuthRepositoryImpl;
import ru.reboot.dto.User;
import ru.reboot.error.BusinessLogicException;
import ru.reboot.error.ErrorCodes;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class DeleteServiceImplTest {
    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private AuthRepositoryImpl authRepository;

    @Test
    public void positiveDeleteUserByIdTest() {
        MockitoAnnotations.initMocks(this);
        when(authRepository.findUserByUserId("1")).thenReturn(new User());
        authService.deleteUser("1");
        verify(authRepository).deleteUserId("1");
    }

    @Test
    public void negativeDeleteUserByIdTest() {
        MockitoAnnotations.initMocks(this);
        doThrow(new BusinessLogicException("User with that login or userId already exists", ErrorCodes.USER_NOT_FOUND.name())).when(authRepository).deleteUserId("2");
        try{
            authService.deleteUser("2");
        }
        catch (BusinessLogicException exception){
            Assert.assertEquals("USER_NOT_FOUND", exception.getCode());
        }
    }

    @Test
    public void twoNegativeDeleteUserByIdTest() {
        MockitoAnnotations.initMocks(this);
        doThrow(new BusinessLogicException("userId is empty or null", ErrorCodes.ILLEGAL_ARGUMENT.name())).when(authRepository).deleteUserId(null);
        try{
            authService.deleteUser(null);
            Assert.fail();
        }
        catch (BusinessLogicException exception){
            Assert.assertEquals("ILLEGAL_ARGUMENT", exception.getCode());
        }
    }
}