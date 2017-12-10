package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.dto.request.SignInRequest;
import ru.kpfu.itis.dto.request.SignUpRequest;
import ru.kpfu.itis.dto.response.Response;
import ru.kpfu.itis.dto.response.SignInResponse;
import ru.kpfu.itis.dto.response.SignUpResponse;
import ru.kpfu.itis.service.AuthService;

@RestController
@RequestMapping(value = "/api/v1")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/users")
    public Response<SignUpResponse> signUp(@RequestBody SignUpRequest request) {
        SignUpResponse response = authService.signUp(request);
        return new Response<>(201, response, null);
    }

    @PostMapping("/login")
    public Response<SignInResponse> login(@RequestBody SignInRequest request) {
        SignInResponse response = authService.signIn(request);
        return new Response<>(200, response, null);
    }

    /*@GetMapping("/users")
    public Response<>*/

}
