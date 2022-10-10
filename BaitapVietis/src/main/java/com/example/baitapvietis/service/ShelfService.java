package com.example.baitapvietis.service;

import com.example.baitapvietis.model.entity.ShelfEntity;

import java.util.List;
import java.util.Optional;

public interface ShelfService {
    List<ShelfEntity> getALl();

    Optional<ShelfEntity> findById(Long id);

    ShelfEntity create(ShelfEntity shelfEntity);

    ShelfEntity update(Long id, ShelfEntity shelfEntity);

    void delete(Long id);

    List<ShelfEntity> search(Long id);
}
