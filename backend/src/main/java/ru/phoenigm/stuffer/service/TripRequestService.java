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
import java.util.Set;

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

    public List<TripRequest> getTripRequestsForMe() {
        return requestRepository.findAllByDriverIdAndStatus(currentUser().getId(),
                TripRequest.TripRequestStatus.UNDER_CONSIDERATION);
    }

    public List<TripRequest> getMyTripRequests() {
        return requestRepository.findAllByStufferIdAndStatus(currentUser().getId(),
                TripRequest.TripRequestStatus.UNDER_CONSIDERATION);
    }

    @Transactional
    public TripRequest sendTripRequest(TripRequestForm form) {
        Optional<Trip> tripOptional = tripRepository.findById(form.getTripId());

        if (tripOptional.isPresent()) {
            Trip trip = tripOptional.get();

            if (trip.getStatus() != Trip.TripStatus.READY) {
                throw new RuntimeException("You can't send request to trip with status " + trip.getStatus());
            }

            if (trip.getStuffers().contains(currentUser())) {
                throw new RuntimeException("You already in trip");
            }

            if (requestRepository.existsByTripIdAndStufferId(trip.getId(), currentUser().getId())) {
                throw new RuntimeException("You already sent request to this trip");
            }

            TripRequest tripRequest = TripRequest.builder()
                    .trip(trip)
                    .driver(trip.getAuthor())
                    .stuffer(currentUser())
                    .requestText(form.getRequestText())
                    .status(TripRequest.TripRequestStatus.UNDER_CONSIDERATION)
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

            if (request.getStatus() != TripRequest.TripRequestStatus.ACCEPTED) {
                throw new RuntimeException("You can't cancel not accepted request");
            }
            request.setStatus(TripRequest.TripRequestStatus.DELETED);
            requestRepository.save(request);
            tripRepository.deleteByStufferIdAndTripId(currentUser().getId(), tripId);

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

            if (confirmation.getStatus() == TripRequest.TripRequestStatus.ACCEPTED) {
                Trip trip = tripRequest.getTrip();
                trip.getStuffers().add(tripRequest.getStuffer());
                tripRepository.save(trip);
            }
            tripRequest.setStatus(confirmation.getStatus());
            requestRepository.save(tripRequest);
            return;
        }
        throw new RuntimeException("Trip request not found");
    }
}
