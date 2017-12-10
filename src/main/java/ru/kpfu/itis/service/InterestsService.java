package ru.kpfu.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.converter.AnnotationConverter;
import ru.kpfu.itis.dto.common.SingleInterest;
import ru.kpfu.itis.exceptions.shared.NoContentException;
import ru.kpfu.itis.exceptions.shared.NoSuchIdException;
import ru.kpfu.itis.model.Interest;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.repo.InterestsRepo;
import ru.kpfu.itis.repo.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class InterestsService {

    private UserRepo userRepo;
    private InterestsRepo interestsRepo;

    @Autowired
    public InterestsService(InterestsRepo interestsRepo,
                            UserRepo userRepo) {
        this.interestsRepo = interestsRepo;
        this.userRepo = userRepo;
    }

    public List<SingleInterest> getInterests() {
        List<Interest> interests = interestsRepo.findAll();
        List<SingleInterest> interestsDto = AnnotationConverter.convertArray(interests, SingleInterest.class);
        return interestsDto;
    }

    public void chooseInterests(Long userId, List<SingleInterest> interests) {
        if (interests == null) {
            throw new NoContentException("You didn't choose any interest");
        }

        Optional<User> mayBeUser = userRepo.findById(userId);
        if (!mayBeUser.isPresent()) {
            throw new NoSuchIdException("User with id = " + userId + " don't exist!");
        }

        User user = mayBeUser.get();
        List<Interest> userInterests = AnnotationConverter.convertArray(interests, Interest.class);
        user.addInterests(userInterests);
        userRepo.save(user);
    }
}
