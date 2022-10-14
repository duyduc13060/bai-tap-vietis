package com.example.baitapvietis.service;

import com.example.baitapvietis.model.entity.WarehouseEntity;

import java.util.List;
import java.util.Optional;

public interface WarehouseService {
    List<WarehouseEntity> getAll();

    Optional<WarehouseEntity> findById(Long id);

    WarehouseEntity createWasehouse(WarehouseEntity warehouseEntity);

    WarehouseEntity updateWasehouse(Long id, WarehouseEntity warehouseEntity);

    boolean deleteWasehouse(Long id);
}
