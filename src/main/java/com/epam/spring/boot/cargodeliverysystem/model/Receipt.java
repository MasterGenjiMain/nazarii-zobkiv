package com.epam.spring.boot.cargodeliverysystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "receipt")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;        //Relation

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private User manager;     //Relation

    @Column(nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "receipt_status_id", referencedColumnName = "id")
    private ReceiptStatus receiptStatus;   //Relation

    @ManyToOne
    @JoinColumn(name = "delivery_order_id", referencedColumnName = "id")
    private DeliveryOrder deliveryOrder;   //Relation
}
