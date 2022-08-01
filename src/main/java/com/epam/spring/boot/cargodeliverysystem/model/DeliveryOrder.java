package com.epam.spring.boot.cargodeliverysystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "delivery_order")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "location_from_id", referencedColumnName = "id")
    private Location locationFrom;         //Relation
    @ManyToOne
    @JoinColumn(name = "location_to_id", referencedColumnName = "id")
    private Location locationTo;           //Relation

    @Column(nullable = false)
    private String cargoName;
    private String cargoDescription;
    @Column(nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "delivery_type_id", referencedColumnName = "id")
    private DeliveryType deliveryType;        //Relation

    @Column(nullable = false)
    private double weight;
    @Column(nullable = false)
    private double volume;
    private Timestamp receivingDate;

    @ManyToOne
    @JoinColumn(name = "tariff_id", referencedColumnName = "id")
    private Tariff tariff;              //Relation
}
