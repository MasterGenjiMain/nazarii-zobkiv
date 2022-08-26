package com.epam.spring.boot.cargodeliverysystem.repository.imp;

import com.epam.spring.boot.cargodeliverysystem.exception.EntityNotFoundException;
import com.epam.spring.boot.cargodeliverysystem.model.Tariff;
import com.epam.spring.boot.cargodeliverysystem.repository.TariffRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class TariffRepositoryImpl implements TariffRepository {

    private final List<Tariff> tariffList = new ArrayList<>();

    @Override
    public List<Tariff> getAllTariffs() {
        log.info("[Repository] getAllTariffs {} ", "");
        return tariffList;
    }

    @Override
    public Tariff getTariffByName(String tariffName) {
        log.info("[Repository] getTariff by name {} ", tariffName);
        return tariffList.stream()
                .filter(t -> t.getTariffName().equals(tariffName))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Tariff> getTariffsByLanguageId(long id) {
        log.info("[Repository] getTariffByLanguageId by id {} ", id);
        return tariffList.stream()
                .filter(tariff -> tariff.getLanguageId() == id)
                .toList();
    }

    @Override
    public Tariff addTariff(Tariff tariff) {
        log.info("[Repository] addTariff {} ", tariff);
        tariffList.add(tariff);
        return tariff;
    }

    @Override
    public Tariff updateTariff(String tariffName, Tariff tariff) {
        log.info("[Repository] updateTariff by name {} ", tariffName);
        boolean isDeleted = tariffList.removeIf(t -> t.getTariffName().equals(tariffName));
        if(isDeleted) {
            tariffList.add(tariff);
        } else {
            throw new EntityNotFoundException();
        }
        return tariff;
    }

    @Override
    public boolean deleteTariff(String tariffName) {
        log.info("[Repository] deleteTariff by name {} ", tariffName);
        return tariffList.removeIf(t -> t.getTariffName().equals(tariffName));
    }
}
