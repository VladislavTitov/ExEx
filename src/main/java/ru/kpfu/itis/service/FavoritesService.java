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
public class FavoritesService {

    private UserRepo userRepo;
    private CourseRepo courseRepo;

    @Autowired
    public FavoritesService(UserRepo userRepo, CourseRepo courseRepo) {
        this.userRepo = userRepo;
        this.courseRepo = courseRepo;
    }

    public void makeFavorite(Long userId, Long courseId) {
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

        course.addLiker(user);
        user.addFavoriteCourse(course);
        userRepo.save(user);
    }

    public void unmakeFavorite(Long userId, Long courseId) {
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

        course.removeLiker(user);
        user.removeFavoriteCourse(course);
        userRepo.save(user);
    }

    public List<ProfileResponse> getAllLikers(Long courseId) {
        Optional<Course> mayBeCourse = courseRepo.findById(courseId);
        if (!mayBeCourse.isPresent()) {
            throw new NoSuchIdException("Course with id = " + courseId + " doesn't exist!");
        }
        Course course = mayBeCourse.get();

        List<ProfileResponse> response = AnnotationConverter.convertArray(course.getLikers(), ProfileResponse.class);
        return response;
    }

}
