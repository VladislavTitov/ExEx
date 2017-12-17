package ru.kpfu.itis.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.model.Lesson;

public interface LessonRepo extends JpaRepository<Lesson, Long> {
}
