package ru.phoenigm.stuffer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.phoenigm.stuffer.domain.TripRequest;
import ru.phoenigm.stuffer.domain.form.TripRequestConfirmation;
import ru.phoenigm.stuffer.domain.form.TripRequestForm;
import ru.phoenigm.stuffer.service.TripRequestService;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class TripRequestController {
    @Autowired
    private TripRequestService requestService;

    @GetMapping
    public List<TripRequest> tripRequests(@RequestParam("filter") String filter) {
        if ("my".equals(filter)) {
            return requestService.getMyTripRequests();
        } else if ("toMe".equals(filter)) {
            return requestService.getTripRequestsForMe();
        } else {
            return Collections.emptyList();
        }
    }

    @PostMapping
    public TripRequest sendTripRequest(@RequestBody TripRequestForm request) {
        return requestService.sendTripRequest(request);
    }

    @PatchMapping
    public ResponseEntity<?> cancelTripRequest(@RequestParam("tripId") Long tripId) {
        requestService.cancelTripRequest(tripId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity processTripRequest(@RequestBody TripRequestConfirmation confirmation) {
        requestService.processTripRequest(confirmation);
        return ResponseEntity.ok().build();
    }
}
