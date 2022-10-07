package com.example.baitapvietis.repository;

import com.example.baitapvietis.model.entity.HandyTerminalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HandyTerminalRepository extends JpaRepository<HandyTerminalEntity,Long> {
}
