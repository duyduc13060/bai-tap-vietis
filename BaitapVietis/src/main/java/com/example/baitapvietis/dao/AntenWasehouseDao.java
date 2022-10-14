package com.example.baitapvietis.dao;

import com.example.baitapvietis.model.dto.AntenWasehouseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AntenWasehouseDao {

    private final EntityManager entityManager;

    public List<AntenWasehouseDto> getAntenWasehouse(){
        String strQuery =
                ""
                        + "SELECT aten.id as anten_id, "
                        + "aten.gate_name as gate_name, "
                        + "wase.id as wasehouse_id, "
                        + "wase.wasehouse_name as wasehouse_name, "
                        + "aten.port_no as port_no, "
                        + "aten.sheft_id as shelf_id, "
                        + "aten.stage_number as stages_number "
                        + "FROM anten aten "
                        + "INNER JOIN reader_writer reader ON aten.reader_writer_id = reader.id "
                        + "INNER JOIN wasehouse wase ON wase.id = reader.wasehouse_id ";

        Query query =  entityManager.createNativeQuery(strQuery,"AntenWasehouseDto");
        List<AntenWasehouseDto> list = query.getResultList();
        return list;
    }


}
