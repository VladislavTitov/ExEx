package ru.kpfu.itis.model;

import javax.persistence.*;

@Entity
@Table(name = "tokens")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @Column(nullable = false, name = "app_id", columnDefinition = "TEXT")
    private String appId;

    @Column(nullable = false)
    private long expired;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User owner;

    public Token() {
    }

    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
