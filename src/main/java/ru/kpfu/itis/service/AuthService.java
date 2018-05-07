package ru.kpfu.itis.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.auth.TokenAuthenticationProvider;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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

        String token = getToken(newUser);

        SignUpResponse response = new SignUpResponse(newUser.getId(), newUser.getLogin(), newUser.getPassword(), token);
        return response;
    }

    public SignInResponse signIn(SignInRequest request) throws InvalidCredentialsException {
        User foundUser = userRepo.findByLoginAndPassword(request.getLogin(), request.getPassword());
        if (foundUser == null) {
            throw new InvalidCredentialsException("Login or password is incorrect!");
        }

        String token = getToken(foundUser);
        SignInResponse response = new SignInResponse(foundUser.getId(), token);
        return response;
    }

    public String getToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "USER");
        claims.put("user_id", user.getId());
        claims.put("username", user.getLogin());

        Calendar exp = Calendar.getInstance();
        exp.add(Calendar.HOUR, 3);

        claims.put("expiration", exp.getTime());

        String token = Jwts.builder()
                .setExpiration(exp.getTime())
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, TokenAuthenticationProvider.SIGN_KEY)
                .compact();
        return token;
    }

}
