package com.example.baitapvietis.service;

import com.example.baitapvietis.model.entity.StatusClassficationEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface StatusClassificationService{
    Optional<StatusClassficationEntity> findById(Long id);

    List<StatusClassficationEntity> findAll();
}
