package com.epam.spring.boot.cargodeliverysystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "location")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String locationName;


    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;        //relation

    @ManyToOne
    @JoinColumn(name = "active_status_id", referencedColumnName = "id")
    private ActiveStatus activeStatus;     //relation
}
