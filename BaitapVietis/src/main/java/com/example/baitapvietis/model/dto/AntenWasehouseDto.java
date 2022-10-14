package com.example.baitapvietis.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@SqlResultSetMapping(
        name = "AntenWasehouseDto",
        classes =
        @ConstructorResult(
                targetClass = AntenWasehouseDto.class,
                columns = {
                        @ColumnResult(name = "anten_id", type = Long.class),
                        @ColumnResult(name = "gate_name", type = String.class),
                        @ColumnResult(name = "wasehouse_id", type = Long.class),
                        @ColumnResult(name = "wasehouse_name", type = String.class),
                        @ColumnResult(name = "port_no", type = Float.class),
                        @ColumnResult(name = "shelf_id", type = Long.class),
                        @ColumnResult(name = "stages_number", type = Float.class),
                }
        )
)

@Entity
@Setter
@Getter
public class AntenWasehouseDto {

    @Id
    private Long anten_id;
    private String gate_name;
    private Long wasehouse_id;
    private String wasehouse_name;
    private Float port_no;
    private Long shelf_id;
    private Float stages_number;


    public AntenWasehouseDto(
            Long anten_id, String gate_name,
            Long wasehouse_id, String wasehouse_name,
            Float port_no, Long shelf_id,
            Float stages_number) {
        this.anten_id = anten_id;
        this.gate_name = gate_name;
        this.wasehouse_id = wasehouse_id;
        this.wasehouse_name = wasehouse_name;
        this.port_no = port_no;
        this.shelf_id = shelf_id;
        this.stages_number = stages_number;
    }

    public AntenWasehouseDto() {
    }
}
