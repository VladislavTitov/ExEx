package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.dto.request.UpdateProfileRequest;
import ru.kpfu.itis.dto.response.ProfileResponse;
import ru.kpfu.itis.dto.response.Response;
import ru.kpfu.itis.service.UsersService;

@RestController
@RequestMapping("/api/v1/users/")
public class UsersController {

    private UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("{user_id}/info")
    public Response<ProfileResponse> getUserInfo(@PathVariable("user_id") Long userId) {
        ProfileResponse response = usersService.getUserInfo(userId);
        return new Response<>(200, response);
    }

    @PutMapping("{user_id}/info")
    public Response<ProfileResponse> updateUserInfo(@PathVariable("user_id") Long userId, @RequestBody UpdateProfileRequest request) {
        ProfileResponse response = usersService.updateUserInfo(userId, request);
        return new Response<>(202, response);
    }

    @DeleteMapping("{user_id}")
    public Response<Object> deleteUser(@PathVariable("user_id") Long userId) {
        usersService.deleteUser(userId);
        return new Response<>(200, null);
    }

}
