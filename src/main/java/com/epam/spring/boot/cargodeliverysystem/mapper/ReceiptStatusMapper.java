package com.epam.spring.boot.cargodeliverysystem.mapper;

import com.epam.spring.boot.cargodeliverysystem.dto.ReceiptStatusDto;
import com.epam.spring.boot.cargodeliverysystem.model.ReceiptStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReceiptStatusMapper {

    ReceiptStatusDto mapReceiptStatusToReceiptStatusDto(ReceiptStatus receiptStatus);

    ReceiptStatus mapReceiptStatusDtoToReceiptStatus(ReceiptStatusDto receiptStatusDto);
}
