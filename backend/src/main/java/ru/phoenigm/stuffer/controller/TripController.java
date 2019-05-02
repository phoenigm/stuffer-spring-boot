package ru.phoenigm.stuffer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.phoenigm.stuffer.domain.Locality;
import ru.phoenigm.stuffer.domain.Region;
import ru.phoenigm.stuffer.domain.Trip;
import ru.phoenigm.stuffer.domain.form.TripRegistrationForm;
import ru.phoenigm.stuffer.domain.form.TripUpdateForm;
import ru.phoenigm.stuffer.payload.TripDto;
import ru.phoenigm.stuffer.service.LocalityService;
import ru.phoenigm.stuffer.service.TripService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trip")
public class TripController {
    @Autowired
    private TripService tripService;

    @Autowired
    private LocalityService localityService;

    @GetMapping("/{id}")
    public ResponseEntity<TripDto> trip(@PathVariable Long id) {
        Optional<TripDto> tripCandidate = tripService.findById(id);
        return tripCandidate.isPresent() ?
                ResponseEntity.ok(tripCandidate.get())
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Trip addTrip(@RequestBody TripRegistrationForm form) {
        return tripService.save(form);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrip(@PathVariable Long id) {
        try {
            tripService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't delete the trip.");
        }
    }

    @PutMapping("/{id}")
    public Trip updateTrip(@PathVariable Long id, @RequestBody TripUpdateForm form, Principal principal) {
        return tripService.updateById(id, form);
    }

    @GetMapping("/all")
    public List<Trip> allTrips() {
        return tripService.getAllTrips();
    }

    @GetMapping("/my")
    public List<Trip> myTrips() {
        return tripService.getMyTrips();
    }

    @GetMapping("/joined")
    public List<Trip> joinedTrips() {
        return tripService.getJoinedTrips();
    }

    @GetMapping("/localities")
    public List<Locality> searchLocality(
            @RequestParam("q") String query,
            @RequestParam("region") String region) {
        return localityService.searchLocality(query, region);
    }

    @GetMapping("/regions")
    public List<Region> searchRegion(@RequestParam("q") String query) {
        return localityService.searchRegion(query);
    }
}
