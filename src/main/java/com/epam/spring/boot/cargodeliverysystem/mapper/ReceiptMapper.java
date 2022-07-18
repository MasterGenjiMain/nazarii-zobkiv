package com.epam.spring.boot.cargodeliverysystem.mapper;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptDto;
import com.epam.spring.boot.cargodeliverysystem.model.Receipt;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReceiptMapper {

    ReceiptDto mapReceiptToReceiptDto(Receipt receipt);

    Receipt mapReceiptDtoToReceipt(ReceiptDto receiptDto);
}
