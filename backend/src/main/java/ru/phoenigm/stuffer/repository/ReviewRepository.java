package ru.phoenigm.stuffer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.phoenigm.stuffer.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT AVG(r.rating) from Review r where r.driver.id = :id")
    Integer getAverageRatingByDriverId(Long id);
}
