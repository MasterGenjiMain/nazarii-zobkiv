package com.epam.spring.boot.cargodeliverysystem.service.impl;

import com.epam.spring.boot.cargodeliverysystem.dto.TariffDto;
import com.epam.spring.boot.cargodeliverysystem.mapper.TariffMapper;
import com.epam.spring.boot.cargodeliverysystem.model.Tariff;
import com.epam.spring.boot.cargodeliverysystem.repository.TariffRepository;
import com.epam.spring.boot.cargodeliverysystem.service.TariffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TariffServiceImpl implements TariffService {

    private final TariffRepository tariffRepository;
    private final TariffMapper tariffMapper;

    @Override
    public List<TariffDto> getAllTariffs() {
        log.info("getAllTariffs {}", "");
        return tariffRepository.getAllTariffs()
                .stream()
                .map(tariffMapper::mapTariffToTariffDto)
                .collect(Collectors.toList());
    }

    @Override
    public TariffDto getTariff(String tariffName) {
        log.info("getTariff by name {}", tariffName);
        Tariff tariff = tariffRepository.getTariffByName(tariffName);
        return tariffMapper.mapTariffToTariffDto(tariff);
    }

    @Override
    public List<TariffDto> getTariffsByLanguageId(long id) {
        log.info("getTariffsByLanguageId {}", id);
        return tariffRepository.getTariffsByLanguageId(id)
                .stream()
                .map(tariffMapper::mapTariffToTariffDto)
                .collect(Collectors.toList());
    }

    @Override
    public TariffDto addTariff(TariffDto tariffDto) {
        log.info("addTariff with name {}", tariffDto.getTariffName());
        Tariff tariff = tariffMapper.mapTariffDtoToTariff(tariffDto);
        tariff = tariffRepository.addTariff(tariff);
        return tariffMapper.mapTariffToTariffDto(tariff);
    }

    @Override
    public TariffDto updateTariff(String tariffName, TariffDto tariffDto) {
        log.info("updateTariff with name {}", tariffName);
        Tariff tariff = tariffMapper.mapTariffDtoToTariff(tariffDto);

        Tariff oldTariff = tariffRepository.getTariffByName(tariffName);
        tariff.setId(oldTariff.getId());

        tariff = tariffRepository.updateTariff(tariffName, tariff);
        return tariffMapper.mapTariffToTariffDto(tariff);
    }

    @Override
    public boolean deleteTariff(String tariffName) {
        log.info("deleteTariff with name {}", tariffName);
        return tariffRepository.deleteTariff(tariffName);
    }
}
