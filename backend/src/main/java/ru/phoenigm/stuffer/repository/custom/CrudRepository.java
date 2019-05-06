package ru.phoenigm.stuffer.repository.custom;

import java.util.List;

public interface CrudRepository<T> {
    T findById(Long id);
    List<T> findAll();
    T save(T model);
    void updateById(Long id, T model);
    void deleteById(Long id);
}
