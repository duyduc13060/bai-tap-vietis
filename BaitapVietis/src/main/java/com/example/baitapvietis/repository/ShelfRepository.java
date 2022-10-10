package com.example.baitapvietis.repository;

import com.example.baitapvietis.model.entity.ShelfEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShelfRepository extends JpaRepository<ShelfEntity,Long> {
    Optional<ShelfEntity> findByWasehoueId(Long wasehoueId);

    @Query("select shelf from ShelfEntity shelf where shelf.wasehoueId = :id")
    List<ShelfEntity> findByWasehoue(Long id);

}
