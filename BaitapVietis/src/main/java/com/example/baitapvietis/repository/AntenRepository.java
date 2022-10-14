package com.example.baitapvietis.repository;

import com.example.baitapvietis.model.dto.AntenWasehouseDto;
import com.example.baitapvietis.model.entity.AntenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AntenRepository extends JpaRepository<AntenEntity,Long> {

//    @Query("SELECT aten FROM AntenEntity aten " +
//            "INNER JOIN ReaderWriterEntity reader ON aten.readerWriterId = reader.id " +
//            "INNER JOIN WasehouseEntity wase ON wase.id = reader.wasehouseId")
//    List<?> listAnten();


//    @Query("SELECT aten.id AS anten_id, aten.gateName AS gate_name, ware.id AS wasehouse_id, ware.wasehouseName AS wasehouse_name, " +
//            "aten.portNo AS port_no, aten.sheftId AS sheft_id, aten.stageNumber AS stage_number FROM AntenEntity aten " +
//            "INNER JOIN ReaderWriterEntity reader ON aten.readerWriterId = reader.id " +
//            "INNER JOIN WarehouseEntity ware ON ware.id = reader.wasehouseId " +
//            "WHERE ware.id = :id")
//    List<AntenWasehouseDto> searchByWarehouseId(Long id);

    @Query("SELECT new com.example.baitapvietis.model.dto.AntenWasehouseDto(aten.id,aten.gateName,ware.id,ware.wasehouseName,aten.portNo,aten.sheftId,aten.stageNumber) FROM AntenEntity aten " +
            "INNER JOIN ReaderWriterEntity reader ON aten.readerWriterId = reader.id " +
            "INNER JOIN WarehouseEntity ware ON ware.id = reader.wasehouseId " +
            "WHERE ware.id = :id")
    List<AntenWasehouseDto> searchByWarehouseId(Long id);


    @Query("SELECT new com.example.baitapvietis.model.dto.AntenWasehouseDto(aten.id,aten.gateName,ware.id,ware.wasehouseName,aten.portNo,aten.sheftId,aten.stageNumber) FROM AntenEntity aten " +
            "INNER JOIN ReaderWriterEntity reader ON aten.readerWriterId = reader.id " +
            "INNER JOIN WarehouseEntity ware ON ware.id = reader.wasehouseId " +
            "WHERE aten.gateName LIKE ?1")
    List<AntenWasehouseDto> searchByName(String gateName);



}
