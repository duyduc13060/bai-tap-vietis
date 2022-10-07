package com.example.baitapvietis.repository;

import com.example.baitapvietis.model.entity.StatusClassficationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusClassficationRepository extends JpaRepository<StatusClassficationEntity,Long> {
}
