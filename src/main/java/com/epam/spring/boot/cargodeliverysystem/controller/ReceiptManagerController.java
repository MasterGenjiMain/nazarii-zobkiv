package com.epam.spring.boot.cargodeliverysystem.controller;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;
import com.epam.spring.boot.cargodeliverysystem.service.ReceiptManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/receiptManager")
@RequiredArgsConstructor
public class ReceiptManagerController {

    private final ReceiptManagerService receiptManagerService;

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "nextStatus/{id}")
    ReceiptDto setNextReceiptStatus(@PathVariable Long id) {
        log.info("[ApproveController] setNextReceiptStatus by id {} ", id);
        return receiptManagerService.nextStatus(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "approve/{id}")
    ReceiptDto approveReceipt(@PathVariable Long id){
        log.info("[ApproveController] approveReceipt by id {} ", id);
        return receiptManagerService.approve(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "cancel/{id}")
    ReceiptDto cancelReceipt(@PathVariable Long id) {
        log.info("[ApproveController] cancelReceipt by id {} ", id);
        return receiptManagerService.cancel(id);
    }
}
