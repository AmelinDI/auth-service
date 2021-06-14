package ru.reboot.service;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.reboot.dao.AuthRepositoryImpl;
import ru.reboot.dto.User;
import ru.reboot.error.BusinessLogicException;

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

    @Test(expected = BusinessLogicException.class)
    public void negativeDeleteUserByIdTest() {
        MockitoAnnotations.initMocks(this);
        doThrow(new BusinessLogicException("BusinessLogicException", "in negativeDeleteUserByIdTest")).when(authRepository).deleteUserId("2");
        authService.deleteUser("2");
    }
}