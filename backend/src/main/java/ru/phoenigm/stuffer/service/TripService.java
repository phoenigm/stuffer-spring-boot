package ru.phoenigm.stuffer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.phoenigm.stuffer.domain.Trip;
import ru.phoenigm.stuffer.domain.User;
import ru.phoenigm.stuffer.domain.form.TripRegistrationForm;
import ru.phoenigm.stuffer.domain.form.TripUpdateForm;
import ru.phoenigm.stuffer.payload.TripDto;
import ru.phoenigm.stuffer.repository.TripRepository;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private LocalityService localityService;

    private User currentUser() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        return (User) userDetailsService.loadUserByUsername(principal.getName());
    }

    @Transactional
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    @Transactional
    public List<Trip> getMyTrips() {
        return tripRepository.findAllByAuthorEmail(currentUser().getEmail());
    }

    @Transactional
    public List<Trip> getJoinedTrips() {
        return tripRepository.findAllByStuffersEmail(currentUser().getEmail());
    }

    @Transactional
    public List<Trip> searchTrips(String from, String to) {
        if (from != null && to != null) {
            return tripRepository.findAllByDepartureLocalityNameIgnoreCaseContainingAndDeliveryLocalityNameIgnoreCaseContaining(from, to);
        }
        if (from != null) {
            return tripRepository.findAllByDepartureLocalityNameIgnoreCaseContaining(from);
        } else if (to != null) {
            return tripRepository.findAllByDeliveryLocalityNameIgnoreCaseContaining(to);
        } else {
            throw new RuntimeException("From and to params mustn't be bull");
        }
    }

    @Transactional
    public Trip save(TripRegistrationForm form) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Trip trip = Trip.builder()
                .arrivalDate(LocalDateTime.parse(form.getArrivalDate(), formatter))
                .departureDate(LocalDateTime.parse(form.getDepartureDate(), formatter))
                .departureAddress(form.getDepartureAddress())
                .deliveryAddress(form.getDeliveryAddress())
                .deliveryLocality(localityService.getLocalityById(form.getDeliveryLocalityId()))
                .departureLocality(localityService.getLocalityById(form.getDepartureLocalityId()))
                .price(form.getPrice())
                .info(form.getInfo())
                .publicationDate(LocalDateTime.now())
                .author(currentUser())
                .status(Trip.TripStatus.READY)
                .build();

        return tripRepository.save(trip);
    }

    @Transactional
    public Trip updateById(Long id, TripUpdateForm form) {
        Optional<Trip> tripOptional = tripRepository.findById(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (tripOptional.isPresent()) {
            Trip trip = tripOptional.get();

            if (!trip.getAuthor().getEmail().equals(currentUser().getEmail())) {
                throw new RuntimeException("You can't update not own trip");
            }

            trip.setArrivalDate(LocalDateTime.parse(form.getArrivalDate(), formatter));
            trip.setDepartureDate(LocalDateTime.parse(form.getDepartureDate(), formatter));
            trip.setDeliveryAddress(form.getDeliveryAddress());
            trip.setDepartureAddress(form.getDepartureAddress());
            trip.setPrice(form.getPrice());
            trip.setStatus(form.getStatus());
            trip.setInfo(form.getInfo());
            return tripRepository.save(trip);
        }
        throw new RuntimeException("Trip not found by id " + id);
    }

    @Transactional
    public Optional<TripDto> findById(Long id) {
        Optional<Trip> tripOptional = tripRepository.findById(id);
        if (tripOptional.isPresent()) {
            Trip trip = tripOptional.get();
            TripDto tripDto = TripDto.from(trip);
            tripDto.getAuthor().setDriverRating(reviewService.ratingByUserId(tripDto.getAuthor().getId()));
            return Optional.of(tripDto);
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<Trip> getById(Long id) {
        return tripRepository.findById(id);
    }

    public void deleteById(Long id) {
        tripRepository.deleteById(id);
    }
}
