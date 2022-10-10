package com.example.baitapvietis.service;

import com.example.baitapvietis.model.entity.GateEntity;

import java.util.List;

public interface Gateservice {
    List<GateEntity> getAll();

    GateEntity create(GateEntity gateEntity);

    GateEntity update(Long id, GateEntity gateEntity);

    void delete(Long id);
}
