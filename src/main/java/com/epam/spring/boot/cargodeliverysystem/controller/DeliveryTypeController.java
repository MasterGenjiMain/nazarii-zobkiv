package com.epam.spring.boot.cargodeliverysystem.controller;

import com.epam.spring.boot.cargodeliverysystem.dto.DeliveryTypeDto;
import com.epam.spring.boot.cargodeliverysystem.dto.group.OnCreate;
import com.epam.spring.boot.cargodeliverysystem.dto.group.OnUpdate;
import com.epam.spring.boot.cargodeliverysystem.service.DeliveryTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/deliveryType")
@RequiredArgsConstructor
public class DeliveryTypeController {

    private final DeliveryTypeService deliveryTypeService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{typeName}")
    public DeliveryTypeDto getDeliveryType(@PathVariable String typeName) {
        log.info("[Controller] getDeliveryType by name {} ", typeName);
        return deliveryTypeService.getDeliveryType(typeName);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<DeliveryTypeDto> getAllDeliveryTypes(){
        log.info("[Controller] getAllDeliveryTypes {} ", "");
        return deliveryTypeService.getAllDeliveryTypes();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/language/{id}")
    public List<DeliveryTypeDto> getAllByLanguageId(@PathVariable long id){
        log.info("[Controller] getAllByLanguageId by id {} ", id);
        return deliveryTypeService.getDeliveryTypesByLanguageId(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public DeliveryTypeDto addDeliveryType(@RequestBody @Validated(OnCreate.class) DeliveryTypeDto deliveryTypeDto) {
        log.info("[Controller] addDeliveryType {} ", deliveryTypeDto);
        return deliveryTypeService.addDeliveryType(deliveryTypeDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{typeName}")
    public DeliveryTypeDto updateDeliveryType(@PathVariable String typeName, @RequestBody @Validated(OnUpdate.class) DeliveryTypeDto deliveryTypeDto) {
        log.info("[Controller] updateDeliveryType by name {} ", typeName);
        return deliveryTypeService.updateDeliveryType(typeName, deliveryTypeDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{typeName}")
    public boolean deleteDeliveryType(@PathVariable String typeName) {
        log.info("[Controller] deleteDeliveryType by name {} ", typeName);
        return deliveryTypeService.deleteDeliveryType(typeName);
    }
}
