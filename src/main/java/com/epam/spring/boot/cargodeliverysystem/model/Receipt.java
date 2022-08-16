package com.epam.spring.boot.cargodeliverysystem.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "receipt")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private User manager;

    @Column(nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "receipt_status_id", referencedColumnName = "id")
    private ReceiptStatus receiptStatus;

    @ManyToOne
    @JoinColumn(name = "delivery_order_id", referencedColumnName = "id")
    private DeliveryOrder deliveryOrder;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Receipt receipt = (Receipt) o;
        return id != null && Objects.equals(id, receipt.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
