package com.epam.spring.boot.cargodeliverysystem.controller;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;
import com.epam.spring.boot.cargodeliverysystem.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    @PutMapping(value = "pay/{id}")
    public ReceiptDto pay(@PathVariable Long id){
        log.info("[AccountController] pay by id {} ", id);
        return accountService.pay(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "cansel/{id}")
    public ReceiptDto cansel(@PathVariable Long id){
        log.info("[AccountController] cansel by id {} ", id);
        return accountService.cansel(id);
    }
}
