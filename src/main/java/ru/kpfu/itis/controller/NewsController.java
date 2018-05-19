package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.dto.common.SingleCourse;
import ru.kpfu.itis.dto.response.Response;
import ru.kpfu.itis.service.NewsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class NewsController {

    private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/users/{user_id}/news")
    public Response<List<SingleCourse>> getNews(@PathVariable("user_id") Long userId, @RequestParam("page_number") int pageNumber) {
        List<SingleCourse> response = newsService.getNews(pageNumber, userId);
        return new Response<>(200, response);
    }

}
