package ru.kpfu.itis.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.model.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
    User findByLoginAndPassword(String login, String password);
}
