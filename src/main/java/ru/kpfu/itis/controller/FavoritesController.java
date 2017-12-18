package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.dto.response.ProfileResponse;
import ru.kpfu.itis.dto.response.Response;
import ru.kpfu.itis.service.FavoritesService;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class FavoritesController {

    private FavoritesService favoritesService;

    @Autowired
    public FavoritesController(FavoritesService favoritesService) {
        this.favoritesService = favoritesService;
    }

    @GetMapping("users/{user_id}/courses/{course_id}/likers")
    public Response<List<ProfileResponse>> getAllLikers(@PathVariable("user_id") Long userId,
                                                        @PathVariable("course_id") Long courseId) {
        List<ProfileResponse> response = favoritesService.getAllLikers(courseId);
        return new Response<>(200, response);
    }

    @PutMapping("likes")
    public Response makeFavorite(@RequestParam("user_id") Long userId, @RequestParam("course_id") Long courseId) {
        favoritesService.makeFavorite(userId, courseId);
        return new Response<>(202, null);
    }

    @DeleteMapping("likes")
    public Response unmakeFavorite(@RequestParam("user_id") Long userId, @RequestParam("course_id") Long courseId) {
        favoritesService.unmakeFavorite(userId, courseId);
        return new Response<>(200, null);
    }
}
