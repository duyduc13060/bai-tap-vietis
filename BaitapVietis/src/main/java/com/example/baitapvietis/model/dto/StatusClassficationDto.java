package com.example.baitapvietis.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//@SqlResultSetMapping(
//        name = "StatusDto",
//        classes =
//        @ConstructorResult(
//                targetClass = StatusDto.class,
//                columns = {
//                        @ColumnResult(name = "classification_id", type = Long.class),
//                        @ColumnResult(name = "classification_name", type = String.class),
//                        @ColumnResult(name = "status_id", type = Long.class),
//                        @ColumnResult(name = "status_name", type = String.class),
//                }
//        )
//)

@Entity
@Setter
@Getter
@NoArgsConstructor
public class StatusClassficationDto {

    @Id
    private Long classification_id;
    private String classification_name;
    private Long status_id;
    private String status_name;

    public StatusClassficationDto(Long classification_id, String classification_name, Long status_id, String status_name){
        this.classification_id = classification_id;
        this.classification_name = classification_name;
        this.status_id = status_id;
        this.status_name = status_name;
    }

}
