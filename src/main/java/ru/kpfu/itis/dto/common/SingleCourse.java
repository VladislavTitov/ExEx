package ru.kpfu.itis.dto.common;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import ru.kpfu.itis.converter.SharedField;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "title",
        "summary",
        "cover",
        "students_number"
})
public class SingleCourse {

    @SharedField(name = "course_id")
    @JsonProperty("id")
    private Long id;

    @SharedField(name = "title")
    @JsonProperty("title")
    private String title;

    @SharedField(name = "summary")
    @JsonProperty("summary")
    private String summary;

    @SharedField(name = "cover")
    @JsonProperty("cover")
    private String cover;

    @SharedField(name = "students_number")
    @JsonProperty("students_number")
    private Integer studentsNumber;

    @SharedField(name = "lessons_number")
    @JsonProperty("lessons_number")
    private Integer lessonsNumber;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public SingleCourse() {
    }

    /**
     *
     * @param summary
     * @param cover
     * @param title
     * @param studentsNumber
     */
    public SingleCourse(String title, String summary, String cover, Integer studentsNumber, Integer lessonsNumber) {
        super();
        this.title = title;
        this.summary = summary;
        this.cover = cover;
        this.studentsNumber = studentsNumber;
        this.lessonsNumber = lessonsNumber;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
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

    @JsonProperty("students_number")
    public Integer getStudentsNumber() {
        return studentsNumber;
    }

    @JsonProperty("students_number")
    public void setStudentsNumber(Integer studentsNumber) {
        this.studentsNumber = studentsNumber;
    }

    public Integer getLessonsNumber() {
        return lessonsNumber;
    }

    public void setLessonsNumber(Integer lessonsNumber) {
        this.lessonsNumber = lessonsNumber;
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