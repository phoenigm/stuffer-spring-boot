package ru.phoenigm.stuffer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.phoenigm.stuffer.domain.TripRequest;
import ru.phoenigm.stuffer.domain.form.TripRequestConfirmation;
import ru.phoenigm.stuffer.domain.form.TripRequestForm;
import ru.phoenigm.stuffer.service.TripRequestService;

import java.util.List;

@RestController
@RequestMapping("/api/request")
public class TripRequestController {
    @Autowired
    private TripRequestService requestService;

    @GetMapping("/toMe")
    public List<TripRequest> myTripRequests() {
        return requestService.getMyTripRequests();
    }

    @PostMapping("/send")
    public TripRequest sendTripRequest(@RequestBody TripRequestForm request) {
        return requestService.sendTripRequest(request);
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> sendTripRequest(@RequestParam("tripId") Long tripId) {
        requestService.cancelTripRequest(tripId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/process")
    public ResponseEntity processTripRequest(@RequestBody TripRequestConfirmation confirmation) {
        requestService.processTripRequest(confirmation);
        return ResponseEntity.ok().build();
    }
}
