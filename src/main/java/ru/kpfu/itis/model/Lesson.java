package ru.kpfu.itis.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.kpfu.itis.converter.SharedField;
import ru.kpfu.itis.model.base.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lessons")
public class Lesson implements Model {

    @SharedField(name = "lesson_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @SharedField(name = "name")
    @Column(nullable = false, length = 100)
    private String name;

    @SharedField(name = "course_number")
    @Column(nullable = false)
    private Integer number = 0;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<LessonBlock> blocks;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Lesson() {
    }

    public Lesson(String name, List<LessonBlock> blocks, Course course) {
        this.name = name;
        this.blocks = blocks;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LessonBlock> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<LessonBlock> blocks) {
        this.blocks = blocks;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
