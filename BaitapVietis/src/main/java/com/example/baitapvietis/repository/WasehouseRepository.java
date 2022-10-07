package com.example.baitapvietis.repository;

import com.example.baitapvietis.model.entity.WasehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WasehouseRepository extends JpaRepository<WasehouseEntity,Long> {
}
