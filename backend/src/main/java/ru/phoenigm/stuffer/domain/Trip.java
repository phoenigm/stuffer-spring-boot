package ru.phoenigm.stuffer.domain;

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

    @ManyToOne
    @JoinColumn
    private User author;

    private LocalDateTime publicationDate;
    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;

    private String departurePoint;
    private String deliveryPoint;

    private Long price;
    private String info;

}
