package ru.kpfu.itis.model;

import ru.kpfu.itis.converter.SharedField;
import ru.kpfu.itis.model.base.Model;

import javax.persistence.*;

@Entity
@Table(name = "blocks")
public class LessonBlock implements Model {

    @SharedField(name = "block_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @SharedField(name = "type")
    @Column(nullable = false)
    private String type;

    @SharedField(name = "content")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @SharedField(name = "number")
    @Column(nullable = false)
    private Integer number = 0;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    public LessonBlock() {
    }

    public LessonBlock(String type, String content, Lesson lesson, Integer number) {
        this.type = type;
        this.content = content;
        this.lesson = lesson;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
