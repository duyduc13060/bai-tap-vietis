package com.example.baitapvietis.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "reader_writer")
@Data
public class ReaderWriterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name ="ip_address")
    private String ipAddress;

    private Long wasehouseId;



}
