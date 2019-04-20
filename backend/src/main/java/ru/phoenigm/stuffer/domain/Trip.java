package ru.phoenigm.stuffer.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"stuffers", "reviews"})
@Table
public class Trip {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
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

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Review> reviews;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_to_trip",
            joinColumns = {@JoinColumn(name = "trip_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> stuffers;

    public enum TripStatus {
        IN_TRIP,
        READY,
        FULL,
        COMPLETED
    }


}
