package ru.phoenigm.stuffer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.phoenigm.stuffer.domain.Trip;
import ru.phoenigm.stuffer.domain.User;
import ru.phoenigm.stuffer.domain.form.TripRegistrationForm;
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

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public List<Trip> getAllSuitableTrips(LocalDateTime departureDate) {
        return tripRepository.getTripsByDepartureDate(departureDate);
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
                .build();

        return tripRepository.save(trip);
    }

    public Optional<Trip> getById(Long id) {
        return tripRepository.findById(id);
    }

    public void deleteById(Long id) {
        tripRepository.deleteById(id);
    }
}
