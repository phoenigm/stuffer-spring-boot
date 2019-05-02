package ru.phoenigm.stuffer.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Locality {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    private Region region;

    @OneToMany(mappedBy = "departureLocality", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Trip> departureTrips;
    @OneToMany(mappedBy = "deliveryLocality", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Trip> deliveryTrips;
}
