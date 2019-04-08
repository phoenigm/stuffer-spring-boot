package ru.phoenigm.stuffer.domain.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripRegistrationForm {

    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;

    private String departurePoint;
    private String deliveryPoint;

    private Long price;
    private String info;
}
