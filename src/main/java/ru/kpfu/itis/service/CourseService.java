package ru.kpfu.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.converter.AnnotationConverter;
import ru.kpfu.itis.dto.request.CreateCourseRequest;
import ru.kpfu.itis.dto.common.SingleCourse;
import ru.kpfu.itis.exceptions.shared.NoSuchIdException;
import ru.kpfu.itis.model.Course;
import ru.kpfu.itis.model.Interest;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.repo.CourseRepo;
import ru.kpfu.itis.repo.InterestsRepo;
import ru.kpfu.itis.repo.UserRepo;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private UserRepo userRepo;
    private CourseRepo courseRepo;
    private InterestsRepo interestsRepo;

    @Autowired
    public CourseService(UserRepo userRepo, CourseRepo courseRepo, InterestsRepo interestsRepo) {
        this.userRepo = userRepo;
        this.courseRepo = courseRepo;
        this.interestsRepo = interestsRepo;
    }

    public SingleCourse createCourse(CreateCourseRequest request, Long userId) {
        Optional<User> mayBeUser = userRepo.findById(userId);
        if (!mayBeUser.isPresent()) {
            throw new NoSuchIdException("User with id = " + userId + " doesn't exist!");
        }
        User user = mayBeUser.get();

        Course newCourse = AnnotationConverter.convert(request, Course.class);
        List<Interest> interests = request.getInterests()
                .stream()
                .map(id -> interestsRepo.getOne(id)) //TODO replace by new Interest(), if it is possible
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        newCourse.setInterests(interests);
        newCourse.setOwner(user);
        user.incrementCreated();
        userRepo.save(user);
        Course createdCourse = courseRepo.save(newCourse);
        SingleCourse response = AnnotationConverter.convert(createdCourse, SingleCourse.class);
        return response;
    }

    public SingleCourse getCourse(Long courseId) {
        Optional<Course> maybeCourse = courseRepo.findById(courseId);
        if (!maybeCourse.isPresent()) {
            throw new NoSuchIdException("Course with id = " + courseId + " doesn't exist!");
        }
        Course course = maybeCourse.get();
        SingleCourse response = AnnotationConverter.convert(course, SingleCourse.class);
        return response;
    }

    public List<SingleCourse> getAllCreatedCoursesByUser(Long userId) {
        Optional<User> mayBeUser = userRepo.findById(userId);
        if (!mayBeUser.isPresent()) {
            throw new NoSuchIdException("User with id = " + userId + " doesn't exist!");
        }
        User user = mayBeUser.get();
        List<Course> createdCourses = user.getCreatedCourses();
        List<SingleCourse> response = AnnotationConverter.convertArray(createdCourses, SingleCourse.class);
        return response;
    }

    public List<SingleCourse> getAllRelativeCourses(Long userId) {
        Optional<User> mayBeUser = userRepo.findById(userId);
        if (!mayBeUser.isPresent()) {
            throw new NoSuchIdException("User with id = " + userId + " doesn't exist!");
        }
        User user = mayBeUser.get();
        List<SingleCourse> response = AnnotationConverter.convertArray(user.getRelativeCourses(), SingleCourse.class);
        return response;
    }

    public List<SingleCourse> getAllFavoritesCourses(Long userId) {
        Optional<User> mayBeUser = userRepo.findById(userId);
        if (!mayBeUser.isPresent()) {
            throw new NoSuchIdException("User with id = " + userId + " doesn't exist!");
        }
        User user = mayBeUser.get();
        List<SingleCourse> response = AnnotationConverter.convertArray(user.getFavoriteCourses(), SingleCourse.class);
        return response;
    }

}
