package com.example.baitapvietis.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "authority")
@Data
public class AuthorityEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleId;

    private Long userId;

}
