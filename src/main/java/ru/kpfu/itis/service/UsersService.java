package ru.kpfu.itis.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.converter.AnnotationConverter;
import ru.kpfu.itis.dto.request.UpdateProfileRequest;
import ru.kpfu.itis.dto.response.ProfileResponse;
import ru.kpfu.itis.exceptions.shared.NoSuchIdException;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.repo.UserRepo;

import java.util.Optional;

@Service
public class UsersService {

    private UserRepo userRepo;

    @Autowired
    public UsersService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public ProfileResponse getUserInfo(Long userId) {
        User user = getUser(userId);
        ProfileResponse response = AnnotationConverter.convert(user, ProfileResponse.class);
        return response;
    }

    public ProfileResponse updateUserInfo(Long userId, UpdateProfileRequest request) {
        User user = getUser(userId);
        AnnotationConverter.convert(request, user);
        User updatedUser = userRepo.save(user);
        ProfileResponse response = AnnotationConverter.convert(updatedUser, ProfileResponse.class);
        return response;
    }

    public void deleteUser(Long userId) {
        userRepo.deleteById(userId);
    }

    @NotNull
    private User getUser(Long userId) {
        Optional<User> mayBeUser = userRepo.findById(userId);
        if (!mayBeUser.isPresent()) {
            throw new NoSuchIdException("User with id = " + userId + " doesn't exist!");
        }
        return mayBeUser.get();
    }

}
