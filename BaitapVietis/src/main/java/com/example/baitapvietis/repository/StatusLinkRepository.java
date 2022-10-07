package com.example.baitapvietis.repository;

import com.example.baitapvietis.model.entity.StatusLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusLinkRepository extends JpaRepository<StatusLinkEntity,Long> {

}
