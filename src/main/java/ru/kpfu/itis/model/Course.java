package ru.kpfu.itis.model;

import ru.kpfu.itis.converter.SharedField;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @SharedField(name = "course_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @SharedField(name = "title")
    @Column(length = 100, nullable = false)
    private String title;

    @SharedField(name = "summary")
    @Column(length = 3000, nullable = false)
    private String summary;

    @SharedField(name = "cover")
    private String cover;

    @SharedField(name = "students_number")
    @Column(name = "students_column", nullable = false)
    private Integer studentsNumber = 0;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @ManyToMany
    @JoinTable(name = "users_courses",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> students;

    public Course() {
    }

    public Course(String title, String summary, String cover, Integer studentsNumber, User owner, List<User> students) {
        this.title = title;
        this.summary = summary;
        this.cover = cover;
        this.studentsNumber = studentsNumber;
        this.owner = owner;
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getStudentsNumber() {
        return studentsNumber;
    }

    public void setStudentsNumber(Integer studentsNumber) {
        this.studentsNumber = studentsNumber;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getStudents() {
        return students;
    }
}
