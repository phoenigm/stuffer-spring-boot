package ru.phoenigm.stuffer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private User reviewer;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private User driver;

    @OneToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    private LocalDateTime reviewDate;
    private Integer rating;

    @Column(columnDefinition = "text")
    private String review;
}
