package ru.phoenigm.stuffer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.phoenigm.stuffer.domain.Trip;

import java.time.LocalDateTime;
import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findAllByDepartureDate(LocalDateTime departureDate);
    List<Trip> findAllByAuthorEmail(String email);
}
