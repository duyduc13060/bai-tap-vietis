package com.example.baitapvietis.repository;

import com.example.baitapvietis.model.dto.StatusClassficationDto;
import com.example.baitapvietis.model.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<StatusEntity,Long> {



    @Query("SELECT new com.example.baitapvietis.model.dto.StatusClassficationDto(status.id,status.name,classi.id,classi.name) FROM StatusEntity status " +
            "INNER JOIN StatusClassficationEntity classi ON status.classFicationId = classi.id")
    List<StatusClassficationDto> listStatusClassification();


}
