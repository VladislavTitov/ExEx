package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.converter.AnnotationConverter;
import ru.kpfu.itis.dto.common.Block;
import ru.kpfu.itis.dto.common.SingleLesson;
import ru.kpfu.itis.exceptions.shared.NoSuchIdException;
import ru.kpfu.itis.model.Course;
import ru.kpfu.itis.model.Lesson;
import ru.kpfu.itis.model.LessonBlock;
import ru.kpfu.itis.repo.CourseRepo;
import ru.kpfu.itis.repo.LessonRepo;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService {

    private LessonRepo lessonRepo;
    private CourseRepo courseRepo;

    public LessonService(LessonRepo lessonRepo, CourseRepo courseRepo) {
        this.lessonRepo = lessonRepo;
        this.courseRepo = courseRepo;
    }

    public SingleLesson createLesson(SingleLesson request, Long courseId) {
        Optional<Course> mayBeCourse = courseRepo.findById(courseId);
        if (!mayBeCourse.isPresent()) {
            throw new NoSuchIdException("Course with id = " + courseId + " doesn't exist!");
        }
        Course course = mayBeCourse.get();

        List<LessonBlock> newBlocks = AnnotationConverter.convertArray(request.getBlocks(), LessonBlock.class);
        Lesson newLesson = AnnotationConverter.convert(request, Lesson.class);
        newBlocks.forEach(lessonBlock -> {
            lessonBlock.setLesson(newLesson);
        });
        //List<LessonBlock> savedBlocks = blockRepo.saveAll(newBlocks);
        newLesson.setBlocks(newBlocks);
        newLesson.setCourse(course);

        Lesson createdLesson = lessonRepo.save(newLesson);
        course.incrementLessonsNumber();
        courseRepo.save(course);

        List<Block> responseBlocks = AnnotationConverter.convertArray(createdLesson.getBlocks(), Block.class);
        SingleLesson response = AnnotationConverter.convert(createdLesson, SingleLesson.class);
        response.setBlocks(responseBlocks);
        return response;
    }

    public SingleLesson getLesson(Long lessonId) {
        Optional<Lesson> mayBeLesson = lessonRepo.findById(lessonId);
        if (!mayBeLesson.isPresent()) {
            throw new NoSuchIdException("Lesson with id = " + lessonId + " doesn't exist!");
        }
        Lesson foundLesson = mayBeLesson.get();
        List<LessonBlock> blocks = foundLesson.getBlocks();
        SingleLesson response = AnnotationConverter.convert(foundLesson, SingleLesson.class);
        List<Block> responseBlocks = AnnotationConverter.convertArray(blocks, Block.class);
        response.setBlocks(responseBlocks);
        return response;
    }

    public List<SingleLesson> getAllLessons(Long courseId) {
        Optional<Course> mayBeCourse = courseRepo.findById(courseId);
        if (!mayBeCourse.isPresent()) {
            throw new NoSuchIdException("Course with id = " + courseId + " doesn't exist!");
        }
        Course foundCourse = mayBeCourse.get();

        List<SingleLesson> response = AnnotationConverter.convertArray(foundCourse.getLessons(), SingleLesson.class);
        return response;
    }

}
