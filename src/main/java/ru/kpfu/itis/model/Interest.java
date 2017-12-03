package ru.kpfu.itis.model;

import ru.kpfu.itis.converter.SharedField;

import javax.persistence.*;

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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPercentage() {
        return percentage;
    }
}
