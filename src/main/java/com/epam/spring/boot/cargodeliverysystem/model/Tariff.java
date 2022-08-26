package com.epam.spring.boot.cargodeliverysystem.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tariff")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String tariffName;
    @Column(nullable = false)
    private double tariffPrice;
    private String tariffInfo;

    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    private Language language;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Tariff tariff = (Tariff) o;
        return id != null && Objects.equals(id, tariff.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
