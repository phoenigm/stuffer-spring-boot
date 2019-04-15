package ru.phoenigm.stuffer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.phoenigm.stuffer.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
