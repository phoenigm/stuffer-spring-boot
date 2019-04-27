package ru.phoenigm.stuffer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.phoenigm.stuffer.domain.Review;
import ru.phoenigm.stuffer.domain.form.ReviewForm;
import ru.phoenigm.stuffer.service.ReviewService;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> create(@RequestBody ReviewForm reviewForm) {

        return ResponseEntity.ok(reviewService.createReview(reviewForm));
    }

}
