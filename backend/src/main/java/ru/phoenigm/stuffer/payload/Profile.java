package ru.phoenigm.stuffer.payload;

import lombok.Builder;
import lombok.Data;
import org.hibernate.Hibernate;
import ru.phoenigm.stuffer.domain.Review;
import ru.phoenigm.stuffer.domain.Trip;
import ru.phoenigm.stuffer.domain.User;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Profile {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String avatarUrl;

    private Integer totalTrips;
    private Double driverRating;
    private Integer reviewsCount;
    private List<Review> lastReviews;
    private List<Trip> lastTrips;

    public static Profile fromUser(User user) {
        return Profile.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .avatarUrl(user.getAvatarUrl())
                .totalTrips(user.getPublishedTrips().size())
                .reviewsCount(user.getReviewsOnMe().size())
                .lastReviews(user.getReviewsOnMe())
                .lastTrips(user.getPublishedTrips())
                .build();
    }
}
