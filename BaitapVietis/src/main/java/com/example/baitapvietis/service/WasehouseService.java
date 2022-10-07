package com.example.baitapvietis.service;

import com.example.baitapvietis.model.entity.WasehouseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface WasehouseService {
    List<WasehouseEntity> getAll();

    Optional<WasehouseEntity> findById(Long id);

    WasehouseEntity createWasehouse(WasehouseEntity wasehouseEntity);

    WasehouseEntity updateWasehouse(Long id, WasehouseEntity wasehouseEntity);

    boolean deleteWasehouse(Long id);
}
