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

    @Transactional
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    @Transactional
    public List<Trip> getMyTrips(String email) {
        return tripRepository.findAllByAuthorEmail(email);
    }

    public List<Trip> getAllSuitableTrips(LocalDateTime departureDate) {
        return tripRepository.findAllByDepartureDate(departureDate);
    }

    @Transactional
    public Trip save(TripRegistrationForm registrationForm) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) userDetailsService.loadUserByUsername(principal.getName());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Trip trip = Trip.builder()
                .arrivalDate(LocalDateTime.parse(registrationForm.getArrivalDate(), formatter))
                .departureDate(LocalDateTime.parse(registrationForm.getDepartureDate(), formatter))
                .departurePoint(registrationForm.getDeparturePoint())
                .deliveryPoint(registrationForm.getDeliveryPoint())
                .price(registrationForm.getPrice())
                .info(registrationForm.getInfo())
                .publicationDate(LocalDateTime.now())
                .author(currentUser)
                .status(Trip.TripStatus.READY)
                .build();

        return tripRepository.save(trip);
    }

    @Transactional
    public Trip updateById(Long id, TripUpdateForm form, Principal principal) {
        Optional<Trip> tripOptional = tripRepository.findById(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (tripOptional.isPresent()) {
            Trip trip = tripOptional.get();

            if (!trip.getAuthor().getEmail().equals(principal.getName())) {
                throw new RuntimeException("You can't update not own trip");
            }

            trip.setArrivalDate(LocalDateTime.parse(form.getArrivalDate(), formatter));
            trip.setDepartureDate(LocalDateTime.parse(form.getDepartureDate(), formatter));
            trip.setDeliveryPoint(form.getDeliveryPoint());
            trip.setDeparturePoint(form.getDeparturePoint());
            trip.setPrice(form.getPrice());
            trip.setStatus(form.getStatus());
            trip.setInfo(form.getInfo());
            return tripRepository.save(trip);
        }
        throw new RuntimeException("Trip not found by id " + id);
    }

    @Transactional
    public Optional<Trip> findById(Long id) {
        return tripRepository.findById(id);
    }

    public void deleteById(Long id) {
        tripRepository.deleteById(id);
    }
}
