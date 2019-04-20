package ru.phoenigm.stuffer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.phoenigm.stuffer.domain.Trip;
import ru.phoenigm.stuffer.domain.TripRequest;
import ru.phoenigm.stuffer.domain.User;
import ru.phoenigm.stuffer.domain.form.TripRequestConfirmation;
import ru.phoenigm.stuffer.domain.form.TripRequestForm;
import ru.phoenigm.stuffer.repository.TripRepository;
import ru.phoenigm.stuffer.repository.TripRequestRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class TripRequestService {

    @Autowired
    private TripService tripService;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TripRequestRepository requestRepository;


    @Autowired
    private UserDetailsService userDetailsService;

    private User currentUser() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        return (User) userDetailsService.loadUserByUsername(principal.getName());
    }

    public List<TripRequest> getMyTripRequests() {
        return requestRepository.findAllByDriverId(currentUser().getId());
    }

    @Transactional
    public TripRequest sendTripRequest(TripRequestForm form) {
        Optional<Trip> tripOptional = tripService.findById(form.getTripId());

        if (tripOptional.isPresent()) {
            Trip trip = tripOptional.get();

            if (trip.getStatus() != Trip.TripStatus.READY) {
                throw new RuntimeException("You can't send request to trip with status " + trip.getStatus());
            }

            if (trip.getStuffers().contains(currentUser())) {
                throw new RuntimeException("You already sent request to this trip");
            }

            TripRequest tripRequest = TripRequest.builder()
                    .trip(trip)
                    .driver(trip.getAuthor())
                    .stuffer(currentUser())
                    .build();
            requestRepository.save(tripRequest);
            return requestRepository.save(tripRequest);

        }
        throw new RuntimeException("Trip with id " + form.getTripId() + " doesn't exist");
    }

    @Transactional
    public void cancelTripRequest(Long tripId) {
        Optional<TripRequest> requestOptional = requestRepository.findByTripId(tripId);

        if (requestOptional.isPresent()) {
            TripRequest request = requestOptional.get();

            if (!request.getStuffer().equals(currentUser())) {
                throw new RuntimeException("This function not allowable for you");
            }

            if (!currentUser().getSentRequests().contains(request)) {
                throw new RuntimeException("You did't send request to trip " + tripId);
            }

            requestRepository.delete(request);
            return;
        }
        throw new RuntimeException("Trip with id " + tripId + " doesn't exist");
    }

    @Transactional
    public void processTripRequest(TripRequestConfirmation confirmation) {
        Optional<TripRequest> tripRequestOptional = requestRepository.findById(confirmation.getTripRequestId());

        if (tripRequestOptional.isPresent()) {
            TripRequest tripRequest = tripRequestOptional.get();

            if (!tripRequest.getDriver().equals(currentUser())) {
                throw new RuntimeException("Illegal access to request");
            }
            Trip trip = tripRequest.getTrip();
            trip.getStuffers().add(tripRequest.getStuffer());
            // todo: persist stuffer to trip
            tripRepository.save(trip);
            requestRepository.delete(tripRequest);
        }
        throw new RuntimeException("Trip request not found");
    }
}
