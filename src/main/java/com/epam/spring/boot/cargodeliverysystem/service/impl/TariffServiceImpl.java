package com.epam.spring.boot.cargodeliverysystem.service.impl;

import com.epam.spring.boot.cargodeliverysystem.dto.TariffDto;
import com.epam.spring.boot.cargodeliverysystem.model.Tariff;
import com.epam.spring.boot.cargodeliverysystem.repository.TariffRepository;
import com.epam.spring.boot.cargodeliverysystem.service.TariffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TariffServiceImpl implements TariffService {

    private final TariffRepository tariffRepository;

    @Override
    public TariffDto getTariff(String tariffName) {
        log.info("getTariff by name {}", tariffName);
        Tariff tariff = tariffRepository.getTariff(tariffName);
        return mapTariffToTariffDto(tariff);
    }

    @Override
    public TariffDto addTariff(TariffDto tariffDto) {
        log.info("addTariff with name {}", tariffDto.getTariffName());
        Tariff tariff = mapTariffDtoToTariff(tariffDto);
        tariff = tariffRepository.addTariff(tariff);
        return mapTariffToTariffDto(tariff);
    }

    @Override
    public TariffDto updateTariff(String tariffName, TariffDto tariffDto) {
        log.info("updateTariff with name {}", tariffName);
        Tariff tariff = mapTariffDtoToTariff(tariffDto);
        tariff = tariffRepository.updateTariff(tariffName, tariff);
        return mapTariffToTariffDto(tariff);
    }

    @Override
    public boolean deleteTariff(String tariffName) {
        log.info("deleteTariff with name {}", tariffName);
        return tariffRepository.deleteTariff(tariffName);
    }

    private TariffDto mapTariffToTariffDto(Tariff tariff) {
        return TariffDto.builder()
                .id(tariff.getId())
                .tariffName(tariff.getTariffName())
                .tariffPrice(tariff.getTariffPrice())
                .tariffInfo(tariff.getTariffInfo())
                .languageId(tariff.getLanguageId())
                .build();
    }

    private Tariff mapTariffDtoToTariff(TariffDto tariffDto) {
        return Tariff.builder()
                .id(tariffDto.getId())
                .tariffName(tariffDto.getTariffName())
                .tariffPrice(tariffDto.getTariffPrice())
                .tariffInfo(tariffDto.getTariffInfo())
                .languageId(tariffDto.getLanguageId())
                .build();
    }
}
