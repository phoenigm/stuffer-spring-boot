package ru.phoenigm.stuffer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.phoenigm.stuffer.domain.Trip;
import ru.phoenigm.stuffer.domain.User;
import ru.phoenigm.stuffer.domain.form.TripRegistrationForm;
import ru.phoenigm.stuffer.repository.TripRepository;
import ru.phoenigm.stuffer.repository.UserRepository;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

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

    public Trip save(TripRegistrationForm registrationForm) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) userDetailsService.loadUserByUsername(principal.getName());

        Trip trip = Trip.builder()
                .arrivalDate(registrationForm.getArrivalDate())
                .departureDate(registrationForm.getDepartureDate())
                .departurePoint(registrationForm.getDeparturePoint())
                .deliveryPoint(registrationForm.getDeliveryPoint())
                .price(registrationForm.getPrice())
                .info(registrationForm.getInfo())
                .publicationDate(LocalDateTime.now())
                .author(currentUser)
                .build();

        return tripRepository.save(trip);
    }

    public Trip getById(Long id) {
        return tripRepository.getOne(id);
    }

    public void deleteById(Long id) {
        tripRepository.deleteById(id);
    }
}
