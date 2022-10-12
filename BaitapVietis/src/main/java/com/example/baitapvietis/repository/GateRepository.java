package com.example.baitapvietis.repository;

import com.example.baitapvietis.model.entity.GatesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GateRepository extends JpaRepository<GatesEntity,Long> {
}
