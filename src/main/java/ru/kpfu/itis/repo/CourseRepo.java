package ru.kpfu.itis.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.model.Course;
import ru.kpfu.itis.model.User;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course, Long> {

    List<Course> findAllByOwner(User owner);

    List<Course> findAllByInterestId(Long interestId);

}
