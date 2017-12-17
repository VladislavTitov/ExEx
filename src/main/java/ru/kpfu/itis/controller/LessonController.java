package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.dto.common.SingleLesson;
import ru.kpfu.itis.dto.response.Response;
import ru.kpfu.itis.service.LessonService;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class LessonController {

    private LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping("/users/{user_id}/courses/{course_id}/lessons")
    public Response<SingleLesson> createLesson(@PathVariable("user_id") Long userId,
                                               @PathVariable("course_id") Long courseId,
                                               @RequestBody SingleLesson request) {
        SingleLesson response = lessonService.createLesson(request, courseId);
        return new Response<>(201, response);
    }

    @GetMapping("/users/{user_id}/courses/{course_id}/lessons/{lesson_id}")
    public Response<SingleLesson> getLesson(@PathVariable("user_id") Long userId,
                                            @PathVariable("course_id") Long courseId,
                                            @PathVariable("lesson_id") Long lessonId) {
        SingleLesson response = lessonService.getLesson(lessonId);
        return new Response<>(200, response);
    }

    @GetMapping("/users/{user_id}/courses/{course_id}/lessons")
    public Response<List<SingleLesson>> getAllLesson(@PathVariable("user_id") Long userId,
                                            @PathVariable("course_id") Long courseId) {
        List<SingleLesson> response = lessonService.getAllLessons(courseId);
        return new Response<>(200, response);
    }
}
