package ru.kpfu.itis.dto.request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import ru.kpfu.itis.converter.ListSharedField;
import ru.kpfu.itis.converter.SharedField;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title",
        "summary",
        "cover",
        "interest_id"
})
public class CreateCourseRequest {

    @SharedField(name = "title")
    @JsonProperty("title")
    private String title;

    @SharedField(name = "summary")
    @JsonProperty("summary")
    private String summary;

    @SharedField(name = "cover")
    @JsonProperty("cover")
    private String cover;

    @ListSharedField(name = "interests", genericType = Long.class)
    @JsonProperty("interests")
    private List<Long> interests;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public CreateCourseRequest() {
    }

    /**
     *
     * @param summary
     * @param cover
     * @param title
     */
    public CreateCourseRequest(String title, String summary, String cover) {
        super();
        this.title = title;
        this.summary = summary;
        this.cover = cover;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("summary")
    public String getSummary() {
        return summary;
    }

    @JsonProperty("summary")
    public void setSummary(String summary) {
        this.summary = summary;
    }

    @JsonProperty("cover")
    public String getCover() {
        return cover;
    }

    @JsonProperty("cover")
    public void setCover(String cover) {
        this.cover = cover;
    }

    public List<Long> getInterests() {
        return interests;
    }

    public void setInterests(List<Long> interests) {
        this.interests = interests;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}