package com.example.baitapvietis.repository;

import com.example.baitapvietis.model.entity.ShelfEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShelfRepository extends JpaRepository<ShelfEntity,Long> {
    Optional<ShelfEntity> findByWasehoueId(Long wasehoueId);
}
