package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.dto.request.CreateCourseRequest;
import ru.kpfu.itis.dto.common.SingleCourse;
import ru.kpfu.itis.dto.response.Response;
import ru.kpfu.itis.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("{user_id}/courses")
    public Response<SingleCourse> createCourse(@PathVariable("user_id") Long userId, @RequestBody CreateCourseRequest request) {
        SingleCourse response = courseService.createCourse(request, userId);
        return new Response<>(201, response);
    }

    @GetMapping("{user_id}/courses")
    public Response<List<SingleCourse>> getAllCourses(@PathVariable("user_id") Long userId,
                                                                  @RequestParam(name = "type") String type) {
        List<SingleCourse> response = null;

        if ("created".equals(type)) {
            response = courseService.getAllCreatedCoursesByUser(userId);
        } else if ("relative".equals(type)) {
            response = courseService.getAllRelativeCourses(userId);
        } else if ("favorite".equals(type)) {
            response = courseService.getAllFavoritesCourses(userId);
        }

        return new Response<>(200, response);
    }

    @GetMapping("{user_id}/courses/{course_id}")
    public Response<SingleCourse> getCourse(@PathVariable("user_id") Long userId, @PathVariable("course_id") Long courseId) {
        SingleCourse response = courseService.getCourse(courseId);
        return new Response<>(200, response);
    }
}
