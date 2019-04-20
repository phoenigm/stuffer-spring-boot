package ru.phoenigm.stuffer.domain.form;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewForm {
    private Long driverId;
    private Long tripId;
    private Double rating;
    private String review;
}
