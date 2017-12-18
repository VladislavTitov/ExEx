package ru.kpfu.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.converter.AnnotationConverter;
import ru.kpfu.itis.dto.response.ProfileResponse;
import ru.kpfu.itis.exceptions.shared.NoSuchIdException;
import ru.kpfu.itis.model.Course;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.repo.CourseRepo;
import ru.kpfu.itis.repo.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class RelativeService {

    private UserRepo userRepo;
    private CourseRepo courseRepo;

    @Autowired
    public RelativeService(UserRepo userRepo, CourseRepo courseRepo) {
        this.userRepo = userRepo;
        this.courseRepo = courseRepo;
    }

    public void makeRelative(Long userId, Long courseId) {
        Optional<User> mayBeUser = userRepo.findById(userId);
        if (!mayBeUser.isPresent()) {
            throw new NoSuchIdException("User with id = " + userId + " doesn't exist!");
        }
        User user = mayBeUser.get();

        Optional<Course> mayBeCourse = courseRepo.findById(courseId);
        if (!mayBeCourse.isPresent()) {
            throw new NoSuchIdException("Course with id = " + courseId + " doesn't exist!");
        }
        Course course = mayBeCourse.get();

        course.addStudent(user);
        Course savedCourse = courseRepo.save(course);
        user.addRelativeCourse(savedCourse);
        userRepo.save(user);
    }

    public void unmakeRelative(Long userId, Long courseId) {
        Optional<User> mayBeUser = userRepo.findById(userId);
        if (!mayBeUser.isPresent()) {
            throw new NoSuchIdException("User with id = " + userId + " doesn't exist!");
        }
        User user = mayBeUser.get();

        Optional<Course> mayBeCourse = courseRepo.findById(courseId);
        if (!mayBeCourse.isPresent()) {
            throw new NoSuchIdException("Course with id = " + courseId + " doesn't exist!");
        }
        Course course = mayBeCourse.get();

        //course.removeStudent(user);
        user.removeRelativeCourse(course);
        courseRepo.save(course);
        userRepo.save(user);
    }

    public List<ProfileResponse> getAllStudents(Long courseId) {
        Optional<Course> mayBeCourse = courseRepo.findById(courseId);
        if (!mayBeCourse.isPresent()) {
            throw new NoSuchIdException("Course with id = " + courseId + " doesn't exist!");
        }
        Course course = mayBeCourse.get();

        List<ProfileResponse> response = AnnotationConverter.convertArray(course.getStudents(), ProfileResponse.class);
        return response;
    }

}
