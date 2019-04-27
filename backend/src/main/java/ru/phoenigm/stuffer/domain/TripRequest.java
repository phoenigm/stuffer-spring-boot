package ru.phoenigm.stuffer.domain;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "trip_request")
public class TripRequest {
    @Id
    @GeneratedValue
    private Long id;
    private String requestText;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_id")
    private User driver;

    @ManyToOne
    private Trip trip;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stuffer_id")
    private User stuffer;

    @Enumerated(EnumType.STRING)
    private TripRequestStatus status;

    public enum TripRequestStatus {
        ACCEPTED,
        DECLINED,
        UNDER_CONSIDERATION,
        DELETED
    }

}
