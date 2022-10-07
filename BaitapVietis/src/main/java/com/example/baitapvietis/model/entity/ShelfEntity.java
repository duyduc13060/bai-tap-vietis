package com.example.baitapvietis.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "shelf")
@Data
public class ShelfEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "stages")
    private Integer stages;

    private Long wasehoueId;

}
