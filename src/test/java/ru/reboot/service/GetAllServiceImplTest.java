package ru.reboot.service;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.reboot.dao.AuthRepositoryImpl;
import ru.reboot.dto.User;
import ru.reboot.error.BusinessLogicException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


public class GetAllServiceImplTest {

    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private AuthRepositoryImpl authRepository;

    public List<User> setUpUserList() {
        List<User> users = new ArrayList<>();
        User one = new User();
        User two = new User();
        User three = new User();
        one.setUserId("1");
        two.setUserId("2");
        three.setUserId("3");
        users.add(one);
        users.add(two);
        users.add(three);
        return users;
    }

    @Test
    public void positiveGetAllUsersTest() {
        MockitoAnnotations.initMocks(this);
        when(authRepository.getAllUsers()).thenReturn(setUpUserList());
        Assert.assertEquals(3, authService.getAllUsers().size());
        Assert.assertEquals(1, authService.getAllUsers().stream().filter(a -> a.getUserId().equalsIgnoreCase("1")).count());
    }

    @Test
    public void twoPositiveGetAllUsersTest() {
        MockitoAnnotations.initMocks(this);
        Assert.assertEquals(0, authService.getAllUsers().size());
    }

    @Test
    public void negativeGetAllUsersTest() {
        MockitoAnnotations.initMocks(this);
        when(authRepository.getAllUsers()).thenReturn(null);
        Assert.assertNull(authService.getAllUsers());
    }
}