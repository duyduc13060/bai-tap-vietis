package com.example.baitapvietis.service;

import com.example.baitapvietis.model.entity.GateEntity;

import java.util.List;

public interface GateService {
    List<GateEntity> getAll();

    GateEntity create(GateEntity gateEntity);

    boolean delete(Long id);
}
