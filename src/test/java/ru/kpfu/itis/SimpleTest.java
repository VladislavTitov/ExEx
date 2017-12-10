package ru.kpfu.itis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.kpfu.itis.converter.AnnotationConverter;
import ru.kpfu.itis.dto.request.SignUpRequest;
import ru.kpfu.itis.model.User;

public class SimpleTest {

    SignUpRequest signUpRequest;
    User user;
    User mutableUser;

    @Before
    public void setUp() {
        signUpRequest = new SignUpRequest("a", "b", "c", "d", "e");
        user = new User("a", "b", "c", "d", "e");
        mutableUser = new User("m", null, null, null, null);
    }

    @Test
    public void converterTest() throws InstantiationException, IllegalAccessException {
        User convertedUser = AnnotationConverter.convert(signUpRequest, User.class);
        System.out.println(convertedUser);
        Assert.assertEquals(user, convertedUser);
    }

    @Test
    public void convertObjectToObject() {
        AnnotationConverter.convert(signUpRequest, mutableUser);
        Assert.assertEquals(user, mutableUser);
    }

}
