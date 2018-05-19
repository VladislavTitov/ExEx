package ru.kpfu.itis.model;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Parameter;
import ru.kpfu.itis.converter.ListSharedField;
import ru.kpfu.itis.converter.SharedField;
import ru.kpfu.itis.model.base.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Indexed
@AnalyzerDef(name = "customanalyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
                        @Parameter(name = "language", value = "Russian")
                })
        })
@Entity
@Table(name = "courses")
public class Course implements Model{

    @DocumentId
    @SharedField(name = "course_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Field
    @Analyzer(definition = "customanalyzer")
    @SharedField(name = "title")
    @Column(length = 100, nullable = false)
    private String title;

    @Field
    @Analyzer(definition = "customanalyzer")
    @SharedField(name = "summary")
    @Column(length = 3000, nullable = false)
    private String summary;

    @SharedField(name = "cover")
    private String cover;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date creationDate;

    @ListSharedField(name = "interests", genericType = Interest.class)
    @ManyToMany
    @JoinTable(name = "courses_interests",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "interest_id"))
    @LazyCollection(value = LazyCollectionOption.FALSE)
    private List<Interest> interests;

    @SharedField(name = "students_number")
    @Column(name = "students_number", nullable = false)
    private Integer studentsNumber = 0;

    @SharedField(name = "lessons_number")
    @Column(name = "lessons_number", nullable = false)
    private Integer lessonsNumber = 0;

    @SharedField(name = "likers_number")
    @Column(name = "likers_number", nullable = false)
    private Integer likersNumber = 0;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @ManyToMany
    @JoinTable(name = "likers_courses",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> likers;

    @ManyToMany
    @JoinTable(name = "students_courses",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> students;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Lesson> lessons;

    public Course() {
    }

    public Course(String title, String summary, String cover, Integer studentsNumber, Integer lessonsNumber, User owner, List<User> students) {
        this.title = title;
        this.summary = summary;
        this.cover = cover;
        this.studentsNumber = studentsNumber;
        this.lessonsNumber = lessonsNumber;
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

    /*public Long getInterestId() {
        return interestId;
    }

    public void setInterestId(Long interestId) {
        this.interestId = interestId;
    }*/

    public Integer getStudentsNumber() {
        return studentsNumber;
    }

    public void setStudentsNumber(Integer studentsNumber) {
        this.studentsNumber = studentsNumber;
    }

    public void incrementStudentsNumber() {
        this.studentsNumber++;
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

    public void setStudents(List<User> students) {
        this.students = students;
    }

    public void addStudent(User student) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
        studentsNumber++;
    }

    public void removeStudent(User student) {
        if (students == null) {
            return;
        }
        students.remove(student);
        studentsNumber--;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public Integer getLessonsNumber() {
        return lessonsNumber;
    }

    public void setLessonsNumber(Integer lessonsNumber) {
        this.lessonsNumber = lessonsNumber;
    }

    public void incrementLessonsNumber() {
        this.lessonsNumber++;
    }

    public void decrementLessonsNumber() {
        this.lessonsNumber--;
    }

    public List<User> getLikers() {
        return likers;
    }

    public void setLikers(List<User> likers) {
        this.likers = likers;
    }

    public void addLiker(User liker) {
        if (likers == null) {
            likers = new ArrayList<>();
        }
        likers.add(liker);
        likersNumber++;
    }

    public void removeLiker(User liker) {
        if (likers == null) {
            return;
        }
        likers.remove(liker);
        likersNumber--;
    }

    public Integer getLikersNumber() {
        return likersNumber;
    }

    public void setLikersNumber(Integer likersNumber) {
        this.likersNumber = likersNumber;
    }

    public List<Interest> getInterests() {
        return interests;
    }

    public void setInterests(List<Interest> interests) {
        this.interests = interests;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
