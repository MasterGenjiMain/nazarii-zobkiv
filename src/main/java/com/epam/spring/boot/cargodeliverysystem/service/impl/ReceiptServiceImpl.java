package com.epam.spring.boot.cargodeliverysystem.service.impl;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;
import com.epam.spring.boot.cargodeliverysystem.mapper.ReceiptMapper;
import com.epam.spring.boot.cargodeliverysystem.model.Receipt;
import com.epam.spring.boot.cargodeliverysystem.repository.ReceiptRepository;
import com.epam.spring.boot.cargodeliverysystem.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final ReceiptMapper receiptMapper;

    @Override
    public List<ReceiptDto> getAllReceipts() {
        return receiptRepository.getAllReceipts()
                .stream()
                .map(receiptMapper::mapReceiptToReceiptDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReceiptDto getReceipt(long id) {
        log.info("getReceipt by id {}", id);
        Receipt receipt = receiptRepository.getReceiptById(id);
        return receiptMapper.mapReceiptToReceiptDto(receipt);
    }

    @Override
    public ReceiptDto createReceipt(ReceiptDto receiptDto) {
        log.info("createReceipt with id {}", receiptDto.getId());
        Receipt receipt = receiptMapper.mapReceiptDtoToReceipt(receiptDto);
        receipt = receiptRepository.createReceipt(receipt);
        return receiptMapper.mapReceiptToReceiptDto(receipt);
    }

    @Override
    public ReceiptDto updateReceipt(long id, ReceiptDto receiptDto) {
        log.info("updateReceipt with id {}", id);
        Receipt receipt = receiptMapper.mapReceiptDtoToReceipt(receiptDto);
        receipt = receiptRepository.updateReceipt(id, receipt);
        return receiptMapper.mapReceiptToReceiptDto(receipt);
    }

    @Override
    public boolean deleteReceipt(long id) {
        log.info("deleteReceipt with id {}", id);
        return receiptRepository.deleteReceipt(id);
    }
}
