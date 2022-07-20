package com.epam.spring.boot.cargodeliverysystem.repository.imp;

import com.epam.spring.boot.cargodeliverysystem.exception.EntityNotFoundException;
import com.epam.spring.boot.cargodeliverysystem.model.ReceiptStatus;
import com.epam.spring.boot.cargodeliverysystem.repository.ReceiptStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ReceiptStatusRepositoryImpl implements ReceiptStatusRepository {

    private final List<ReceiptStatus> receiptStatusList = new ArrayList<>();

    @Override
    public ReceiptStatus getReceiptStatus(String statusName) {
        log.info("[Repository] getReceiptStatus by name {} ", statusName);
        return receiptStatusList.stream()
                .filter(s -> s.getStatusName().equals(statusName))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public ReceiptStatus addReceiptStatus(ReceiptStatus receiptStatus) {
        log.info("[Repository] addReceiptStatus by name {} ", receiptStatus);
        receiptStatusList.add(receiptStatus);
        return receiptStatus;
    }

    @Override
    public ReceiptStatus updateReceiptStatus(String statusName, ReceiptStatus receiptStatus) {
        log.info("[Repository] updateReceiptStatus by name {} ", statusName);
        boolean isDeleted = receiptStatusList.removeIf(s -> s.getStatusName().equals(statusName));
        if(isDeleted) {
            receiptStatusList.add(receiptStatus);
        } else {
            throw new EntityNotFoundException();
        }
        return receiptStatus;
    }

    @Override
    public boolean deleteReceiptStatus(String statusName) {
        log.info("[Repository] deleteReceiptStatus by name {} ", statusName);
        return receiptStatusList.removeIf(s -> s.getStatusName().equals(statusName));
    }
}
