package ru.phoenigm.stuffer.repository.custom;

import ru.phoenigm.stuffer.domain.Region;

import java.util.List;

public interface RegionRepository extends CrudRepository<Region> {
    List<Region> findAllByNameIgnoreCaseContaining(String name);
}
