package com.epam.spring.boot.cargodeliverysystem.controller;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptStatusDto;
import com.epam.spring.boot.cargodeliverysystem.service.ReceiptStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/receiptStatuses")
@RequiredArgsConstructor
public class ReceiptStatusController {

    private final ReceiptStatusService receiptStatusService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{statusName}")
    public ReceiptStatusDto getReceiptStatus(@PathVariable String statusName) {
        log.info("[Controller] getReceiptStatus by name {} ", statusName);
        return receiptStatusService.getReceiptStatus(statusName);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ReceiptStatusDto addReceiptStatus(@RequestBody ReceiptStatusDto receiptStatusDto) {
        log.info("[Controller] addReceiptStatus {} ", receiptStatusDto);
        return receiptStatusService.addReceiptStatus(receiptStatusDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{statusName}")
    public ReceiptStatusDto updateReceiptStatus(@PathVariable String statusName, @RequestBody ReceiptStatusDto receiptStatusDto) {
        log.info("[Controller] updateReceiptStatus by name {} ", statusName);
        return receiptStatusService.updateReceiptStatus(statusName, receiptStatusDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{statusName}")
    public boolean deleteReceiptStatus(@PathVariable String statusName) {
        log.info("[Controller] deleteReceiptStatus by name {} ", statusName);
        return receiptStatusService.deleteReceiptStatus(statusName);
    }
}
