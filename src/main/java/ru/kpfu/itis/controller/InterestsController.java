package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.converter.AnnotationConverter;
import ru.kpfu.itis.dto.request.ChooseInterestsRequest;
import ru.kpfu.itis.dto.response.Response;
import ru.kpfu.itis.dto.common.SingleInterest;
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
    public Response<List<SingleInterest>> getInterests() {
        return new Response<>(200, interestsService.getInterests(), null);
    }

    @GetMapping("/users/{user_id}/interests")
    public Response<Object> getUserInterests(@PathVariable("user_id") Long userId) {
        List<SingleInterest> response = interestsService.getUserInterests(userId);
        return new Response<>(200, response, null);
    }

    @PostMapping("/users/{user_id}/interests")
    public Response<Object> chooseInterests(@PathVariable("user_id") Long userId, @RequestBody ChooseInterestsRequest request) {
        interestsService.chooseInterests(userId, request.getInterests());
        return new Response<>(200, null, null);
    }

}
