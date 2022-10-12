package com.example.baitapvietis.repository;

import com.example.baitapvietis.model.dto.AntenWasehouseDto;
import com.example.baitapvietis.model.entity.AntenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AntenRepository extends JpaRepository<AntenEntity,Long> {

//    @Query("SELECT aten FROM AntenEntity aten " +
//            "INNER JOIN ReaderWriterEntity reader ON aten.readerWriterId = reader.id " +
//            "INNER JOIN WasehouseEntity wase ON wase.id = reader.wasehouseId")
//    List<?> listAnten();




}
