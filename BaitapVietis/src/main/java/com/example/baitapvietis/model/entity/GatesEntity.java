package com.example.baitapvietis.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "gate")
@Data
public class GatesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gate_name")
    private String gateName;

    @Column(name = "ip_address")
    private String ipAddress;

    private Long wasehouseId;

}
