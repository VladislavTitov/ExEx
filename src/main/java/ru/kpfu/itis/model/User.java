package ru.kpfu.itis.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.kpfu.itis.converter.SharedField;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @SharedField(name = "login")
    @Column(unique = true, nullable = false)
    private String login;

    @SharedField(name = "password")
    @Column(nullable = false)
    private String password;

    @SharedField(name = "email")
    @Column(unique = true, nullable = false)
    private String email;

    @SharedField(name = "firstname")
    @Column(nullable = false)
    private String firstname;

    @SharedField(name = "lastname")
    @Column(nullable = false)
    private String lastname;

    private Integer age;

    private String gender;

    private Date birthday;

    private String country;

    private String city;

    @Column(columnDefinition = "TEXT")
    private String about;

    @Column(nullable = false)
    @ColumnDefault(value = "0.0")
    private double karma;

    @Column(nullable = false)
    @ColumnDefault(value = "0")
    private int comments;

    @Column(nullable = false)
    @ColumnDefault(value = "0")
    private int likes;

    @Column(nullable = false)
    @ColumnDefault(value = "0")
    private int finished;

    @Column(nullable = false)
    @ColumnDefault(value = "0")
    private int created;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Token> tokens;

    public User() {
    }

    public User(String login, String password, String email, String firstname, String lastname) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public double getKarma() {
        return karma;
    }

    public void setKarma(double karma) {
        this.karma = karma;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Double.compare(user.karma, karma) == 0 &&
                comments == user.comments &&
                likes == user.likes &&
                finished == user.finished &&
                created == user.created &&
                Objects.equals(id, user.id) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(firstname, user.firstname) &&
                Objects.equals(lastname, user.lastname) &&
                Objects.equals(age, user.age) &&
                Objects.equals(gender, user.gender) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(country, user.country) &&
                Objects.equals(city, user.city) &&
                Objects.equals(about, user.about);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email, firstname, lastname, age, gender, birthday, country, city, about, karma, comments, likes, finished, created);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
