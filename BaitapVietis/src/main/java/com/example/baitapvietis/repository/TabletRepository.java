package com.example.baitapvietis.repository;

import com.example.baitapvietis.model.entity.TabletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TabletRepository extends JpaRepository<TabletEntity,Long> {
}
