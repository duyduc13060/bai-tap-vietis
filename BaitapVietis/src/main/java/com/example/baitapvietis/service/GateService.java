package com.example.baitapvietis.service;

import com.example.baitapvietis.model.entity.GatesEntity;

import java.util.List;
import java.util.Optional;

public interface GateService {
    List<GatesEntity> getAll();

    Optional<GatesEntity> findById(Long id);

    GatesEntity create(GatesEntity gateEntity);

    GatesEntity update(Long id, GatesEntity gateEntity);

    void delete(Long id);
}
