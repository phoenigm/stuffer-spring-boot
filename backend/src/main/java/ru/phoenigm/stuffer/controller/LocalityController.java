package ru.phoenigm.stuffer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.phoenigm.stuffer.domain.Locality;
import ru.phoenigm.stuffer.service.LocalityService;

import java.util.List;

@RestController
@RequestMapping("/api/localities")
public class LocalityController {
    @Autowired
    private LocalityService localityService;

    @GetMapping
    public List<Locality> searchLocality(
            @RequestParam("q") String query,
            @RequestParam("region") String region) {
        return localityService.searchLocality(query, region);
    }
}
