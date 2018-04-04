package ru.kpfu.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.converter.AnnotationConverter;
import ru.kpfu.itis.dto.common.SingleCourse;
import ru.kpfu.itis.exceptions.shared.NoSuchIdException;
import ru.kpfu.itis.model.Course;
import ru.kpfu.itis.model.Interest;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.repo.CourseRepo;
import ru.kpfu.itis.repo.UserRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewsService {

    private UserRepo userRepo;
    private CourseRepo courseRepo;

    @Autowired
    public NewsService(UserRepo userRepo, CourseRepo courseRepo) {
        this.userRepo = userRepo;
        this.courseRepo = courseRepo;
    }

    public List<SingleCourse> getNews(Long userId) {
        Optional<User> mayBeUser = userRepo.findById(userId);
        if (!mayBeUser.isPresent()) {
            throw new NoSuchIdException("User with id = " + userId + " doesn't exist!");
        }
        User user = mayBeUser.get();
        List<Course> foundCourses = user.getInterests().stream()
                .map(Interest::getId)
                .map(aLong -> new Course())
                .collect(Collectors.toList());
        List<SingleCourse> response = AnnotationConverter.convertArray(foundCourses, SingleCourse.class);
        return response;
    }

}
