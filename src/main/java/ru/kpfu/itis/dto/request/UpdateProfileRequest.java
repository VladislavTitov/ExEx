package ru.kpfu.itis.dto.request;

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
        "firstname",
        "lastname",
        "gender",
        "birthday",
        "country",
        "city",
        "about"
})
public class UpdateProfileRequest {

    @SharedField(name = "firstname")
    @JsonProperty("firstname")
    private String firstname;

    @SharedField(name = "lastname")
    @JsonProperty("lastname")
    private String lastname;

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

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public UpdateProfileRequest() {
    }

    /**
     *
     * @param birthday
     * @param about
     * @param gender
     * @param lastname
     * @param firstname
     * @param city
     * @param country
     */
    public UpdateProfileRequest(String firstname, String lastname, String gender, Date birthday, String country, String city, String about) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.birthday = birthday;
        this.country = country;
        this.city = city;
        this.about = about;
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
