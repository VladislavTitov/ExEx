package ru.kpfu.itis.dto.response;

import java.util.Date;
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
        "firstname",
        "lastname",
        "age",
        "gender",
        "birthday",
        "country",
        "city",
        "about",
        "karma",
        "comments",
        "likes",
        "finished",
        "created"
})
public class ProfileResponse {

    @SharedField(name = "id")
    @JsonProperty("id")
    private Long id;

    @SharedField(name = "firstname")
    @JsonProperty("firstname")
    private String firstname;

    @SharedField(name = "lastname")
    @JsonProperty("lastname")
    private String lastname;

    @SharedField(name = "age")
    @JsonProperty("age")
    private Integer age;

    @SharedField(name = "gender")
    @JsonProperty("gender")
    private String gender;

    @SharedField(name = "birthday")
    @JsonProperty("birthday")
    private Date birthday;

    @SharedField(name = "country")
    @JsonProperty("country")
    private String country;

    @SharedField(name = "city")
    @JsonProperty("city")
    private String city;

    @SharedField(name = "about")
    @JsonProperty("about")
    private String about;

    @SharedField(name = "karma")
    @JsonProperty("karma")
    private Double karma;

    @SharedField(name = "comments")
    @JsonProperty("comments")
    private Integer comments;

    @SharedField(name = "likes")
    @JsonProperty("likes")
    private Integer likes;

    @SharedField(name = "finished")
    @JsonProperty("finished")
    private Integer finished;

    @SharedField(name = "created")
    @JsonProperty("created")
    private Integer created;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public ProfileResponse() {
    }

    /**
     *
     * @param karma
     * @param birthday
     * @param created
     * @param likes
     * @param age
     * @param about
     * @param gender
     * @param lastname
     * @param finished
     * @param firstname
     * @param comments
     * @param city
     * @param country
     */
    public ProfileResponse(String firstname, String lastname, Integer age, String gender, Date birthday, String country, String city, String about, Double karma, Integer comments, Integer likes, Integer finished, Integer created) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
        this.birthday = birthday;
        this.country = country;
        this.city = city;
        this.about = about;
        this.karma = karma;
        this.comments = comments;
        this.likes = likes;
        this.finished = finished;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("firstname")
    public String getFirstname() {
        return firstname;
    }

    @JsonProperty("firstname")
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @JsonProperty("lastname")
    public String getLastname() {
        return lastname;
    }

    @JsonProperty("lastname")
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @JsonProperty("age")
    public Integer getAge() {
        return age;
    }

    @JsonProperty("age")
    public void setAge(Integer age) {
        this.age = age;
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("birthday")
    public Date getBirthday() {
        return birthday;
    }

    @JsonProperty("birthday")
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("about")
    public String getAbout() {
        return about;
    }

    @JsonProperty("about")
    public void setAbout(String about) {
        this.about = about;
    }

    @JsonProperty("karma")
    public Double getKarma() {
        return karma;
    }

    @JsonProperty("karma")
    public void setKarma(Double karma) {
        this.karma = karma;
    }

    @JsonProperty("comments")
    public Integer getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(Integer comments) {
        this.comments = comments;
    }

    @JsonProperty("likes")
    public Integer getLikes() {
        return likes;
    }

    @JsonProperty("likes")
    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    @JsonProperty("finished")
    public Integer getFinished() {
        return finished;
    }

    @JsonProperty("finished")
    public void setFinished(Integer finished) {
        this.finished = finished;
    }

    @JsonProperty("created")
    public Integer getCreated() {
        return created;
    }

    @JsonProperty("created")
    public void setCreated(Integer created) {
        this.created = created;
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