package ru.phoenigm.stuffer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.phoenigm.stuffer.domain.Region;
import ru.phoenigm.stuffer.service.LocalityService;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
public class RegionController {
    @Autowired
    private LocalityService localityService;

    @GetMapping
    public List<Region> searchRegion(@RequestParam("q") String query) {
        return localityService.searchRegion(query);
    }

}
