package com.epam.spring.boot.cargodeliverysystem.repository;

import com.epam.spring.boot.cargodeliverysystem.model.ReceiptStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReceiptStatusRepository extends JpaRepository<ReceiptStatus, Long> {

    Optional<ReceiptStatus> findByStatusName(String statusName);
}
