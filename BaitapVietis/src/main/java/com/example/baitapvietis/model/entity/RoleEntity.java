package com.example.baitapvietis.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
@Data
public class RoleEntity {

    @Id
    private String id;

    @Column(name = "name")
    private String name;
}
