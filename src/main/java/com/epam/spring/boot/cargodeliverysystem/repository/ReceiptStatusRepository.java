package com.epam.spring.boot.cargodeliverysystem.repository;

import com.epam.spring.boot.cargodeliverysystem.model.ReceiptStatus;

public interface ReceiptStatusRepository {

    ReceiptStatus getReceiptStatus(String statusName);

    ReceiptStatus addReceiptStatus(ReceiptStatus receiptStatus);

    ReceiptStatus updateReceiptStatus(String statusName, ReceiptStatus receiptStatus);

    boolean deleteReceiptStatus(String statusName);
}
