package ru.kpfu.itis.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.model.Token;

public interface TokenRepo extends JpaRepository<Token, Long> {
}
