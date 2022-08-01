package com.epam.spring.boot.cargodeliverysystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "active_status")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActiveStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(nullable = false, unique = true)
    private String activeStatusName;
}
