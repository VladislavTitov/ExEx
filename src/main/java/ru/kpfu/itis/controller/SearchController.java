package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.dto.common.SingleCourse;
import ru.kpfu.itis.dto.response.Response;
import ru.kpfu.itis.service.SearchService;

import java.util.List;

@RestController
@RequestMapping("api/v1/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/courses")
    public Response<List<SingleCourse>> searchCourses(@RequestParam(name = "q") String q) {
        return new Response<>(200, searchService.searchCourses(q));
    }

}
