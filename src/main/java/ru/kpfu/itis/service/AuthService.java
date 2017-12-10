package ru.kpfu.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.converter.AnnotationConverter;
import ru.kpfu.itis.dto.request.SignInRequest;
import ru.kpfu.itis.dto.request.SignUpRequest;
import ru.kpfu.itis.dto.response.SignInResponse;
import ru.kpfu.itis.dto.response.SignUpResponse;
import ru.kpfu.itis.exceptions.shared.InvalidCredentialsException;
import ru.kpfu.itis.model.Token;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.repo.TokenRepo;
import ru.kpfu.itis.repo.UserRepo;
import ru.kpfu.itis.utils.TokenGenerator;

@Service
public class AuthService {

    private UserRepo userRepo;
    private TokenRepo tokenRepo;
    private TokenGenerator tokenGenerator;

    @Autowired
    public AuthService(UserRepo userRepo,
                       TokenRepo tokenRepo,
                       TokenGenerator tokenGenerator) {
        this.userRepo = userRepo;
        this.tokenRepo = tokenRepo;
        this.tokenGenerator = tokenGenerator;
    }

    public SignUpResponse signUp(SignUpRequest request) {
        User newUser = AnnotationConverter.convert(request, User.class);
        newUser = userRepo.save(newUser);

        String token = tokenGenerator.generateToken();
        Token newToken = new Token(token);
        newToken.setOwner(newUser);
        tokenRepo.save(newToken);

        SignUpResponse response = new SignUpResponse(newUser.getId(), newUser.getLogin(), newUser.getPassword(), token);
        return response;
    }

    public SignInResponse signIn(SignInRequest request) throws InvalidCredentialsException {
        User foundUser = userRepo.findByLoginAndPassword(request.getLogin(), request.getPassword());
        if (foundUser == null) {
            throw new InvalidCredentialsException("Login or password is incorrect!");
        }

        String token = tokenGenerator.generateToken();
        Token newToken = new Token(token);
        newToken.setOwner(foundUser);
        tokenRepo.save(newToken);
        SignInResponse response = new SignInResponse(foundUser.getId(), token);
        return response;
    }

}
