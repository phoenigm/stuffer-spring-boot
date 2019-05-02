package ru.phoenigm.stuffer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.phoenigm.stuffer.domain.Locality;
import ru.phoenigm.stuffer.domain.Region;
import ru.phoenigm.stuffer.repository.LocalityRepository;
import ru.phoenigm.stuffer.repository.RegionRepository;

import java.util.List;

@Service
public class LocalityService {

    @Autowired
    private LocalityRepository localityRepository;

    @Autowired
    private RegionRepository regionRepository;

    public List<Locality> searchLocality(String criteria, String region) {
        return localityRepository.findAllByNameIgnoreCaseContainingAndRegionName(
                criteria, region, PageRequest.of(0, 50)).getContent();
    }

    public List<Region> searchRegion(String criteria) {
        return regionRepository.findAllByNameIgnoreCaseContaining(criteria);
    }

    public Locality getLocalityById(Long id) {
        return localityRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Locality with id " + id + " not found"));
    }
}
