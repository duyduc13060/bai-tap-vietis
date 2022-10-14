package com.example.baitapvietis.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "anten")
@Data
public class AntenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "port_no")
    private Float portNo;

    @Column(name = "gate_name")
    private String gateName;

    @Column(name = "stage_number")
    private Float stageNumber;

    private Long sheftId;

    private Long readerWriterId;

}
