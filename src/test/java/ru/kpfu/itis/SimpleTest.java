package ru.kpfu.itis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.kpfu.itis.converter.AnnotationConverter;
import ru.kpfu.itis.dto.common.SingleCourse;
import ru.kpfu.itis.dto.request.SignUpRequest;
import ru.kpfu.itis.model.Course;
import ru.kpfu.itis.model.Interest;
import ru.kpfu.itis.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleTest {

    SignUpRequest signUpRequest;
    User user;
    User mutableUser;

    Course course;
    SingleCourse targetCourse;

    @Before
    public void setUp() {
        signUpRequest = new SignUpRequest("a", "b", "c", "d", "e");
        user = new User("a", "b", "c", "d", "e");
        mutableUser = new User("m", null, null, null, null);


        Interest interest1 = new Interest();
        interest1.setId(1L);
        Interest interest2 = new Interest();
        interest2.setId(2L);
        List<Interest> interests = new ArrayList<>(Arrays.asList(interest1, interest2));

        List<Long> interestIds = new ArrayList<>(Arrays.asList(1L, 2L));

        course = new Course("testTitle", "testSummary", "testCover", 0, 0, null, null);
        course.setId(1L);
        course.setInterests(interests);
        targetCourse = new SingleCourse("testTitle", "testSummary", "testCover", 0, 0);
        targetCourse.setId(1L);
        targetCourse.setLikersNumber(0);
        targetCourse.setInterests(interestIds);
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

    @Test
    public void converterWithListsTest() {
        SingleCourse actualCourse = AnnotationConverter.convert(course, SingleCourse.class);
        Assert.assertEquals(targetCourse, actualCourse);
    }

}
