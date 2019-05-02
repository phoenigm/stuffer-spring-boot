package ru.phoenigm.stuffer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.phoenigm.stuffer.domain.Trip;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findAllByDepartureLocalityNameIgnoreCaseContainingAndDeliveryLocalityNameIgnoreCaseContaining(String from, String to);

    List<Trip> findAllByDepartureLocalityNameIgnoreCaseContaining(String from);

    List<Trip> findAllByDeliveryLocalityNameIgnoreCaseContaining(String to);

    List<Trip> findAllByAuthorEmail(String email);

    @Query("select distinct t from Trip t join t.stuffers s where s.email = :email")
    List<Trip> findAllByStuffersEmail(String email);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM user_to_trip WHERE user_id = :stufferId AND trip_id = :tripId")
    void deleteByStufferIdAndTripId(Long stufferId, Long tripId);
}
