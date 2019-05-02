package ru.phoenigm.stuffer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.phoenigm.stuffer.domain.Locality;

public interface LocalityRepository extends PagingAndSortingRepository<Locality, Long> {
    Page<Locality> findAllByNameIgnoreCaseContainingAndRegionName(String name, String regionName, Pageable pageable);
}
