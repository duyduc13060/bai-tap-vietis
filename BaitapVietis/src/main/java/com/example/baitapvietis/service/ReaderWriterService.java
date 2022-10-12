package com.example.baitapvietis.service;

import com.example.baitapvietis.model.entity.ReaderWriterEntity;

import java.util.List;
import java.util.Optional;

public interface ReaderWriterService {
    List<ReaderWriterEntity> getAll();

    Optional<ReaderWriterEntity> findById(Long id);

    ReaderWriterEntity create(ReaderWriterEntity readerWriterEntity);

    ReaderWriterEntity update(Long id, ReaderWriterEntity readerWriterEntity);

    void delete(Long id);
}
