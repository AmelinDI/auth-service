package ru.reboot;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import ru.reboot.dao.AuthRepository;
import ru.reboot.dto.User;
import ru.reboot.service.AuthServiceImpl;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class AppTest {

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void mockTest() {

        List<String> mockList = Mockito.mock(List.class);
        Mockito.when(mockList.get(10)).thenReturn("Hello");

        Assert.assertEquals("Hello", mockList.get(10));
        Assert.assertNull(mockList.get(11));

        Mockito.verify(mockList).get(10);
        Mockito.verify(mockList).get(11);
    }

    @Test
    public void mockRepositoryTest() {

        // prepare
        AuthRepository repositoryMock = Mockito.mock(AuthRepository.class);
        Mockito.when(repositoryMock.findUserByUserId(Mockito.anyString())).thenReturn(new User());

        AuthServiceImpl service = new AuthServiceImpl();
        service.setAuthRepository(repositoryMock);

        // act
//        service.someMethod(....

        // verify
//        Assert.assertEquals(...
//        Assert.assertEquals(...
    }
}
