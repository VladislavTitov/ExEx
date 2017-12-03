package ru.kpfu.itis.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.model.Interest;

public interface InterestsRepo extends JpaRepository<Interest, Long> {

}
