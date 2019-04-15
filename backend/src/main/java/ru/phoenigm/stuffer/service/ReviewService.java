package ru.phoenigm.stuffer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.phoenigm.stuffer.domain.Review;
import ru.phoenigm.stuffer.domain.User;
import ru.phoenigm.stuffer.domain.form.ReviewForm;
import ru.phoenigm.stuffer.repository.ReviewRepository;
import ru.phoenigm.stuffer.repository.UserRepository;

import java.security.Principal;
import java.time.LocalDateTime;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TripService tripService;

    @Autowired
    private UserDetailsService userDetailsService;


    public Review createReview(ReviewForm reviewForm) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) userDetailsService.loadUserByUsername(principal.getName());

        Review review = Review.builder()
                .review(reviewForm.getReview())
                .rating(reviewForm.getRating())
                .reviewer(currentUser)
                .driver(userService.findById(reviewForm.getDriverId())
                        .orElseThrow(() -> new RuntimeException("Error while review creating")))
                .reviewDate(LocalDateTime.now())
                .trip(tripService.getById(reviewForm.getTripId())
                        .orElseThrow(() -> new RuntimeException("Error while review creating")))
                .build();

        return reviewRepository.save(review);

    }
}
