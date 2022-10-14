package com.example.baitapvietis.service;

import com.example.baitapvietis.model.dto.StatusClassficationDto;
import com.example.baitapvietis.model.entity.StatusEntity;

import java.util.List;

public interface StatusService {
    List<StatusClassficationDto> findAll();

    StatusEntity findById(Long id);

    StatusEntity create(StatusEntity statusEntity);

    StatusEntity update(Long id, StatusEntity statusEntity);

    void delete(Long id);
}
