package ru.phoenigm.stuffer.repository.custom;

import ru.phoenigm.stuffer.domain.Review;

public interface ReviewRepository extends CrudRepository<Review> {
    Double getAverageRatingByDriverId(Long id);
}
