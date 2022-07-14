package com.epam.spring.boot.cargodeliverysystem.repository;

import com.epam.spring.boot.cargodeliverysystem.model.Receipt;

public interface ReceiptRepository {

    Receipt getReceipt(long id);

    Receipt createReceipt(Receipt receipt);

    Receipt updateReceipt(long id, Receipt receipt);

    boolean deleteReceipt (long id);
}
