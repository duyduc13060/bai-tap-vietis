package com.example.baitapvietis.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "link")
@Data
public class LinkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private Long linewordId;

    private Long statusId;


}
