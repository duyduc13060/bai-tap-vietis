package com.example.baitapvietis.repository;

import com.example.baitapvietis.model.entity.ReaderWriterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderWriterRepository extends JpaRepository<ReaderWriterEntity,Long> {
}
