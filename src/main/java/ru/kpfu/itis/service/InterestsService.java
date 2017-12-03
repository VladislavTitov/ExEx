package ru.kpfu.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.converter.AnnotationConverter;
import ru.kpfu.itis.dto.response.SingleInterestResponse;
import ru.kpfu.itis.model.Interest;
import ru.kpfu.itis.repo.InterestsRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterestsService {

    private InterestsRepo interestsRepo;

    @Autowired
    public InterestsService(InterestsRepo interestsRepo) {
        this.interestsRepo = interestsRepo;
    }

    public List<SingleInterestResponse> getInterests() {
        List<Interest> interests = interestsRepo.findAll();
        List<SingleInterestResponse> interestsDto = interests
                .stream()
                .map(interest -> AnnotationConverter.convert(interest, SingleInterestResponse.class))
                .collect(Collectors.toList());
        return interestsDto;
    }
}
