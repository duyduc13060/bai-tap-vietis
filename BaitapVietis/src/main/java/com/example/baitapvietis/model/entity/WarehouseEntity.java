package com.example.baitapvietis.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "wasehouse")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wasehouse_name")
    private String wasehouseName;

    @Column(name = "address")
    private String address;



//    @OneToMany(mappedBy = "wasehouse")
//    private Collection<AntenEntity> antenEntities;


}
