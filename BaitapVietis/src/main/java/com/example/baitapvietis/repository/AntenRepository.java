package com.example.baitapvietis.repository;

import com.example.baitapvietis.model.entity.AntenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AntenRepository extends JpaRepository<AntenEntity,Long> {
}
