package ru.phoenigm.stuffer.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Trip {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    @JsonIgnore
    private User author;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime arrivalDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime departureDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime publicationDate;

    private String departurePoint;
    private String deliveryPoint;

    @Enumerated(EnumType.STRING)
    private TripStatus status;

    private Integer price;

    @Column(columnDefinition = "text")
    private String info;

    @OneToOne(mappedBy = "trip", cascade = CascadeType.ALL)
    @JsonBackReference
    private Review review;

    public enum TripStatus {
        ACTIVE,
        FULL,
        OOMPLETED
    }


}
