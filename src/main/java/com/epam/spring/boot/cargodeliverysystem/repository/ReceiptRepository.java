package com.epam.spring.boot.cargodeliverysystem.repository;

import com.epam.spring.boot.cargodeliverysystem.model.Receipt;

import java.util.List;

public interface ReceiptRepository {

    List<Receipt> getAllReceipts();

    Receipt getReceiptById(long id);

    Receipt createReceipt(Receipt receipt);

    Receipt updateReceipt(long id, Receipt receipt);

    boolean deleteReceipt (long id);
}
