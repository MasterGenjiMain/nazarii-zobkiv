package com.epam.spring.boot.cargodeliverysystem.repository.imp;

import com.epam.spring.boot.cargodeliverysystem.exception.EntityNotFoundException;
import com.epam.spring.boot.cargodeliverysystem.model.Receipt;
import com.epam.spring.boot.cargodeliverysystem.repository.ReceiptRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ReceiptRepositoryImpl implements ReceiptRepository {

    private final List<Receipt> receiptList = new ArrayList<>();

    @Override
    public List<Receipt> getAllReceipts() {
        return receiptList;
    }

    @Override
    public Receipt getReceipt(long id) {
        log.info("[Repository] getReceipt by id {} ", id);
        return receiptList.stream()
                .filter(receipt -> receipt.getId() == id)
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Receipt createReceipt(Receipt receipt) {
        log.info("[Repository] createReceipt {} ", receipt);
        receiptList.add(receipt);
        return receipt;
    }

    @Override
    public Receipt updateReceipt(long id, Receipt receipt) {
        log.info("[Repository] updateReceipt by id {} ", id);
        boolean isDeleted = receiptList.removeIf(r -> r.getId() == id);
        if(isDeleted) {
            receiptList.add(receipt);
        } else {
            throw new EntityNotFoundException();
        }
        return receipt;
    }

    @Override
    public boolean deleteReceipt(long id) {
        log.info("[Repository] deleteReceipt by id {} ", id);
        return receiptList.removeIf(receipt -> receipt.getId() == id);
    }
}
