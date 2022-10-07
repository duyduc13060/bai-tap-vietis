package com.example.baitapvietis.repository;

import com.example.baitapvietis.model.entity.LinkageMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkageMessageRepository extends JpaRepository<LinkageMessageEntity,Long> {
}
