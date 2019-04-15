package ru.phoenigm.stuffer.domain.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripRegistrationForm {
    private String arrivalDate;
    private String departureDate;

    private String departurePoint;
    private String deliveryPoint;

    private Integer price;
    private String info;
}
