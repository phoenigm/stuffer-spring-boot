package ru.phoenigm.stuffer.domain.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.phoenigm.stuffer.domain.Trip;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripUpdateForm {
    private String arrivalDate;
    private String departureDate;
    private String departureAddress;
    private String deliveryAddress;

    private Integer price;
    private String info;

    private Trip.TripStatus status;
}
