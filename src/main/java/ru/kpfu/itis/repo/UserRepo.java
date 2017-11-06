package ru.kpfu.itis.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByLoginAndPassword(String login, String password);
}
