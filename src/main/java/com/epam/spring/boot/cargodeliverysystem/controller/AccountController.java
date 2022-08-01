package com.epam.spring.boot.cargodeliverysystem.controller;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;
import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptStatusDto;
import com.epam.spring.boot.cargodeliverysystem.dto.group.OnUpdate;
import com.epam.spring.boot.cargodeliverysystem.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public List<ReceiptDto> getAllUserReceipts(@PathVariable Long id){
        log.info("[AccountController] getAllUserReceipts by id {} ", id);
        return accountService.giveAllUserReceipts(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public ReceiptDto changeReceiptStatus(@PathVariable Long id, @RequestBody @Validated(OnUpdate.class) ReceiptStatusDto receiptStatusDto){
        log.info("[AccountController] changeReceiptStatus by id {} ", id);
        return accountService.changeReceiptStatus(id, receiptStatusDto);
    }
}
