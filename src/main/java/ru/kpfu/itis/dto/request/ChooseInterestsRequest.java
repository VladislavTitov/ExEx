package ru.kpfu.itis.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.kpfu.itis.dto.common.SingleInterest;

import java.util.List;

public class ChooseInterestsRequest {

    @JsonProperty("interests")
    private List<SingleInterest> interests;

    public ChooseInterestsRequest() {
    }

    public ChooseInterestsRequest(List<SingleInterest> interests) {
        this.interests = interests;
    }

    @JsonProperty("interests")
    public List<SingleInterest> getInterests() {
        return interests;
    }

    @JsonProperty("interests")
    public void setInterests(List<SingleInterest> interests) {
        this.interests = interests;
    }
}
