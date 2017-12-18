package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.dto.response.ProfileResponse;
import ru.kpfu.itis.dto.response.Response;
import ru.kpfu.itis.service.RelativeService;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class RelativeController {

    private RelativeService relativeService;

    @Autowired
    public RelativeController(RelativeService relativeService) {
        this.relativeService = relativeService;
    }

    @GetMapping("users/{user_id}/courses/{course_id}/students")
    public Response<List<ProfileResponse>> getAllStudents(@PathVariable("user_id") Long userId,
                                                          @PathVariable("course_id") Long courseId) {
        List<ProfileResponse> response = relativeService.getAllStudents(courseId);
        return new Response<>(200, response);
    }

    @PutMapping("subscriptions")
    public Response makeRelative(@RequestParam("user_id") Long userId, @RequestParam("course_id") Long courseId) {
        relativeService.makeRelative(userId, courseId);
        return new Response<>(202, null);
    }
    @DeleteMapping("subscriptions")
    public Response unmakeRelative(@RequestParam("user_id") Long userId, @RequestParam("course_id") Long courseId) {
        relativeService.unmakeRelative(userId, courseId);
        return new Response<>(200, null);
    }

}
