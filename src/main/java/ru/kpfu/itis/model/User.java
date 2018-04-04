package ru.kpfu.itis.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.kpfu.itis.converter.SharedField;
import ru.kpfu.itis.model.base.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Model {

    @SharedField(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @SharedField(name = "age")
    private Integer age;

    @SharedField(name = "gender")
    private String gender;

    @SharedField(name = "birthday")
    private Date birthday;

    @SharedField(name = "country")
    private String country;

    @SharedField(name = "city")
    private String city;

    @SharedField(name = "about")
    @Column(columnDefinition = "TEXT")
    private String about;

    @SharedField(name = "karma")
    @Column(nullable = false)
    @ColumnDefault(value = "0.0")
    private Double karma = 0.0;

    @SharedField(name = "comments")
    @Column(nullable = false)
    @ColumnDefault(value = "0")
    private Integer comments = 0;

    @SharedField(name = "likes")
    @Column(nullable = false)
    @ColumnDefault(value = "0")
    private Integer likes = 0;

    @SharedField(name = "finished")
    @Column(nullable = false)
    @ColumnDefault(value = "0")
    private Integer finished = 0;

    @SharedField(name = "created")
    @Column(nullable = false)
    @ColumnDefault(value = "0")
    private Integer created = 0;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    private List<Token> tokens;

    @ManyToMany
    @JoinTable(name = "users_interests",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "interest_id"))
    @LazyCollection(value = LazyCollectionOption.FALSE)
    private List<Interest> interests;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Course> createdCourses;

    @ManyToMany
    @JoinTable(name = "students_courses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Course> relativeCourses;

    @ManyToMany
    @JoinTable(name = "likers_courses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Course> favoriteCourses;

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

    public void incrementCreated() {
        this.created += 1;
    }

    public List<Interest> getInterests() {
        return interests;
    }

    public List<Course> getCreatedCourses() {
        return createdCourses;
    }

    public void addCreatedCourse(Course course) {
        if (createdCourses == null) {
            createdCourses = new ArrayList<>();
        }
        createdCourses.add(course);
        created++;
    }

    public void addCreatedCourses(List<Course> courses) {
        if (createdCourses == null) {
            createdCourses = new ArrayList<>();
        }
        createdCourses.addAll(courses);
        created += courses.size();
    }

    public List<Course> getRelativeCourses() {
        return relativeCourses;
    }

    public void addRelativeCourse(Course course) {
        if (relativeCourses == null) {
            relativeCourses = new ArrayList<>();
        }
        relativeCourses.add(course);
    }

    public void addRelativeCourses(List<Course> courses) {
        if (relativeCourses == null) {
            relativeCourses = new ArrayList<>();
        }
        relativeCourses.addAll(courses);
    }

    public void removeRelativeCourse(Course course) {
        if (relativeCourses == null) {
            return;
        }
        relativeCourses.remove(course);
    }

    public void addInterest(Interest interest) {
        if (this.interests == null) {
            this.interests = new ArrayList<>();
        }
        interests.add(interest);
    }

    public void addInterests(List<Interest> interests) {
        if (this.interests == null) {
            this.interests = new ArrayList<>();
        }
        this.interests.addAll(interests);
    }

    public List<Course> getFavoriteCourses() {
        return favoriteCourses;
    }

    public void setFavoriteCourses(List<Course> favoriteCourses) {
        this.favoriteCourses = favoriteCourses;
    }

    public void addFavoriteCourse(Course favoriteCourse) {
        if (favoriteCourses == null) {
            favoriteCourses = new ArrayList<>();
        }
        favoriteCourses.add(favoriteCourse);
        likes++;
    }

    public void removeFavoriteCourse(Course favoriteCourse) {
        if (favoriteCourses == null) {
            return;
        }
        favoriteCourses.remove(favoriteCourse);
        likes--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
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
                Objects.equals(about, user.about) &&
                Objects.equals(karma, user.karma) &&
                Objects.equals(comments, user.comments) &&
                Objects.equals(likes, user.likes) &&
                Objects.equals(finished, user.finished) &&
                Objects.equals(created, user.created);
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
