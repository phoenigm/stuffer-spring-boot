package ru.phoenigm.stuffer.domain.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewForm {
    private Long driverId;
    private Long tripId;
    private Double rating;
    private String review;
}
