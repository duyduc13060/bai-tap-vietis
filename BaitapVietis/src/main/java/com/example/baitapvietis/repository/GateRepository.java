package com.example.baitapvietis.repository;

import com.example.baitapvietis.model.entity.GateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GateRepository extends JpaRepository<GateEntity, Long> {
}
