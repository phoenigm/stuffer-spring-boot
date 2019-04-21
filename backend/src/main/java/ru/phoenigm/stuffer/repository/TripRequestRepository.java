package ru.phoenigm.stuffer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.phoenigm.stuffer.domain.TripRequest;

import java.util.List;
import java.util.Optional;

public interface TripRequestRepository extends JpaRepository<TripRequest, Long> {
    Optional<TripRequest> findByTripId(Long tripId);
    List<TripRequest> findAllByDriverIdAndStatus(Long driverId, TripRequest.TripRequestStatus status);
}
