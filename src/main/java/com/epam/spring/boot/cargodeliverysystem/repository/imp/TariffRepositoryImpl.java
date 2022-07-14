package com.epam.spring.boot.cargodeliverysystem.repository.imp;

import com.epam.spring.boot.cargodeliverysystem.model.Tariff;
import com.epam.spring.boot.cargodeliverysystem.repository.TariffRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class TariffRepositoryImpl implements TariffRepository {

    private final List<Tariff> tariffList = new ArrayList<>();

    @Override
    public Tariff getTariff(String tariffName) {
        log.info("[Repository] getTariff by name {} ", tariffName);
        return tariffList.stream()
                .filter(t -> t.getTariffName().equals(tariffName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Tariff is not found!"));
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
            throw new RuntimeException("Tariff is not found!");
        }
        return tariff;
    }

    @Override
    public boolean deleteTariff(String tariffName) {
        log.info("[Repository] deleteTariff by name {} ", tariffName);
        return tariffList.removeIf(t -> t.getTariffName().equals(tariffName));
    }
}
