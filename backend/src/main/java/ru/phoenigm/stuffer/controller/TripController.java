package ru.phoenigm.stuffer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.phoenigm.stuffer.domain.Trip;
import ru.phoenigm.stuffer.domain.form.TripRegistrationForm;
import ru.phoenigm.stuffer.domain.form.TripUpdateForm;
import ru.phoenigm.stuffer.payload.TripDto;
import ru.phoenigm.stuffer.service.LocalityService;
import ru.phoenigm.stuffer.service.TripService;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trips")
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

    @GetMapping
    public List<Trip> trips(@RequestParam("type") String type,
                            @RequestParam(value = "from", required = false) String from,
                            @RequestParam(value = "to", required = false) String to) {
        switch (type) {
            case "search":
                return tripService.searchTrips(from, to);
            case "all":
                return tripService.getAllTrips();
            case "my":
                return tripService.getMyTrips();
            case "joined":
                return tripService.getJoinedTrips();
            default:
                return Collections.emptyList();
        }
    }

    @PostMapping
    public Trip addTrip(@RequestBody TripRegistrationForm form) {
        return tripService.save(form);
    }

    @PutMapping("/{id}")
    public Trip updateTrip(@PathVariable Long id, @RequestBody TripUpdateForm form, Principal principal) {
        return tripService.updateById(id, form);
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
}
