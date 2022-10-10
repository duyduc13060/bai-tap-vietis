package com.example.baitapvietis.service;

import com.example.baitapvietis.model.entity.AntenEntity;

import java.util.List;
import java.util.Optional;

public interface AntenService {
    List<AntenEntity> getAll();

    Optional<AntenEntity> findById(Long id);

    AntenEntity create(AntenEntity antenEntity);

    AntenEntity update(Long id, AntenEntity antenEntity);

    void delete(Long id);
}
