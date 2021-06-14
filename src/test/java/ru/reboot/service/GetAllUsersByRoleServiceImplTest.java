package ru.reboot.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.reboot.dao.AuthRepositoryImpl;
import ru.reboot.dto.User;
import ru.reboot.error.BusinessLogicException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.when;

public class GetAllUsersByRoleServiceImplTest {
    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private AuthRepositoryImpl authRepository;


    @Before
    public void setUpMock() {
        MockitoAnnotations.initMocks(this);
    }

    public List<User> setUpUserList() {
        List<User> users = new ArrayList<>();
        User one = new User();
        User two = new User();
        User three = new User();
        one.setRole("admin");
        two.setRole("user");
        three.setRole("superadmin");
        users.add(one);
        users.add(two);
        users.add(three);
        return users;
    }

    @Test
    public void negativeGetAllUsersByRoleTest() {
        Collection<String> roles = new ArrayList<>();
        roles.add("admin");
        roles.add("user");
        when(authRepository.getAllUsers()).thenReturn(setUpUserList());
        Assert.assertEquals(2, authService.getAllUsersByRole(roles).size());
    }

    @Test
    public void positiveGetAllUsersByRoleTest() {
        Collection<String> roles = new ArrayList<>();
        roles.add("bigBoss");
        roles.add("darkwingDuck");
        Collection<String> secondBatchOfRoles = new ArrayList<>();
        secondBatchOfRoles.add("superadmin");

        when(authRepository.getAllUsers()).thenReturn(setUpUserList());
        Assert.assertEquals("superadmin", authService.getAllUsersByRole(secondBatchOfRoles).get(0).getRole());
        Assert.assertNotEquals(authService.getAllUsersByRole(roles).size(), 2);
    }

    @Test
    public void twoNegativeGetAllUsersByRoleTest() {
        Collection<String> roles = new ArrayList<>();
        when(authRepository.getAllUsers()).thenReturn(setUpUserList());
        try {
            authService.getAllUsersByRole(roles);
        } catch (BusinessLogicException exception) {
            Assert.assertEquals("ILLEGAL_ARGUMENT", exception.getCode());
        }

    }
}