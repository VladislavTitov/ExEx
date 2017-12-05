package ru.kpfu.itis.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.kpfu.itis.converter.SharedField;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "interests")
public class Interest {

    @SharedField(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @SharedField(name = "name")
    private String name;

    @SharedField(name = "percentage")
    private Double percentage;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_interests",
            joinColumns = @JoinColumn(name = "interest_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @LazyCollection(value = LazyCollectionOption.FALSE)
    private List<User> users;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPercentage() {
        return percentage;
    }

    public List<User> getUsers() {
        return users;
    }
}
