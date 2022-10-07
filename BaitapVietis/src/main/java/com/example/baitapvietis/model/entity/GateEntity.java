package com.example.baitapvietis.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "gate")
@Data
public class GateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gate_name")
    private String gateName;

    @Column(name = "ip_address")
    private String ipAddress;

    private Long wasehouseId;

}
