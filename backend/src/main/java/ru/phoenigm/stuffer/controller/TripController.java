package ru.phoenigm.stuffer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.phoenigm.stuffer.domain.Trip;
import ru.phoenigm.stuffer.domain.form.TripRegistrationForm;
import ru.phoenigm.stuffer.service.TripService;

import java.util.List;

@RestController
@RequestMapping("/api/trip")
public class TripController {
    @Autowired
    private TripService tripService;

    @GetMapping
    public Trip trip(@RequestParam("id") Long id) {
        return tripService.getById(id);
    }

    @PostMapping
    public Trip addTrip(@RequestBody TripRegistrationForm form) {
        return tripService.save(form);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTrip(@RequestParam("id") Long id) {
        try {
            tripService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't delete the trip.");
        }
    }

    @GetMapping("/all")
    public List<Trip> allTrips() {
        return tripService.getAllTrips();
    }
}
