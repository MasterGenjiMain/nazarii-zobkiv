package com.epam.spring.boot.cargodeliverysystem.controller;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;
import com.epam.spring.boot.cargodeliverysystem.dto.group.OnCreate;
import com.epam.spring.boot.cargodeliverysystem.dto.group.OnUpdate;
import com.epam.spring.boot.cargodeliverysystem.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/receipts")
@RequiredArgsConstructor
public class ReceiptController {

    private final ReceiptService receiptService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public ReceiptDto getReceipt(@PathVariable long id) {
        log.info("[Controller] getReceipt by id {} ", id);
        return receiptService.getReceipt(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ReceiptDto> getAllReceipts() {
        log.info("[Controller] getAllReceipts {} ", "");
        return receiptService.getAllReceipts();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ReceiptDto createReceipt(@RequestBody @Validated(OnCreate.class) ReceiptDto receiptDto) {
        log.info("[Controller] createReceipt {} ", receiptDto);
        return receiptService.createReceipt(receiptDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public ReceiptDto updateReceipt(@PathVariable long id, @RequestBody @Validated(OnUpdate.class) ReceiptDto receiptDto) {
        log.info("[Controller] updateReceipt by id {} ", id);
        return receiptService.updateReceipt(id, receiptDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public boolean deleteReceipt(@PathVariable long id) {
        log.info("[Controller] deleteReceipt by id {} ", id);
        return receiptService.deleteReceipt(id);
    }
}
