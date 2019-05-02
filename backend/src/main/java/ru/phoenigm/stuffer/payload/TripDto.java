package ru.phoenigm.stuffer.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.phoenigm.stuffer.domain.Locality;
import ru.phoenigm.stuffer.domain.Review;
import ru.phoenigm.stuffer.domain.Trip;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripDto {
    private Long id;
    private Profile author;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime arrivalDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime departureDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime publicationDate;

    private Locality departureLocality;
    private String departureAddress;
    private Locality deliveryLocality;
    private String deliveryAddress;

    private Trip.TripStatus status;
    private Integer price;
    private String info;
    private List<Review> reviews;
    private Set<StufferDto> stuffers;

    public static TripDto from(Trip trip) {
        return TripDto.builder()
                .id(trip.getId())
                .author(Profile.fromUser(trip.getAuthor()))
                .arrivalDate(trip.getArrivalDate())
                .departureDate(trip.getDepartureDate())
                .publicationDate(trip.getPublicationDate())
                .departureAddress(trip.getDepartureAddress())
                .departureLocality(trip.getDepartureLocality())
                .deliveryAddress(trip.getDeliveryAddress())
                .deliveryLocality(trip.getDeliveryLocality())
                .status(trip.getStatus())
                .price(trip.getPrice())
                .info(trip.getInfo())
                .stuffers(trip.getStuffers()
                        .stream()
                        .map(StufferDto::from)
                        .collect(Collectors.toSet()))
                .build();
    }
}
