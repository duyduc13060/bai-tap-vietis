package com.example.baitapvietis.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@SqlResultSetMapping(
        name = "AntenWasehouseDto",
        classes =
        @ConstructorResult(
                targetClass = AntenWasehouseDto.class,
                columns = {
                        @ColumnResult(name = "anten_id", type = Long.class),
                        @ColumnResult(name = "wasehouse_id", type = Long.class),
                        @ColumnResult(name = "wasehouse_name", type = String.class),
                        @ColumnResult(name = "port_no", type = String.class),
                        @ColumnResult(name = "shelf_id", type = Long.class),
                        @ColumnResult(name = "stages_number", type = Float.class),
                }
        )
)

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AntenWasehouseDto {

    @Id
    private Long anten_id;
    private Long wasehouse_id;
    private String wasehouse_name;
    private String port_no;
    private Long shelf_id;
    private Float stages_number;




}
