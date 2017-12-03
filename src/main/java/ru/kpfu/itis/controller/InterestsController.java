package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.dto.response.Response;
import ru.kpfu.itis.dto.response.SingleInterestResponse;
import ru.kpfu.itis.service.InterestsService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class InterestsController {

    private InterestsService interestsService;

    @Autowired
    public InterestsController(InterestsService interestsService) {
        this.interestsService = interestsService;
    }

    @GetMapping("/interests")
    public Response<List<SingleInterestResponse>> getInterests() {
        return new Response<>(200, interestsService.getInterests(), null);
    }

}
