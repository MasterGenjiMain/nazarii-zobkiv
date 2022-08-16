package com.epam.spring.boot.cargodeliverysystem.service;

import static com.epam.spring.boot.cargodeliverysystem.test.util.TestDataUtil.TEST_LANGUAGE_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import com.epam.spring.boot.cargodeliverysystem.dto.LocationDto;
import com.epam.spring.boot.cargodeliverysystem.dto.TariffDto;
import com.epam.spring.boot.cargodeliverysystem.mapper.LocationMapper;
import com.epam.spring.boot.cargodeliverysystem.mapper.LocationMapperImpl;
import com.epam.spring.boot.cargodeliverysystem.mapper.TariffMapper;
import com.epam.spring.boot.cargodeliverysystem.mapper.TariffMapperImpl;
import com.epam.spring.boot.cargodeliverysystem.model.Location;
import com.epam.spring.boot.cargodeliverysystem.model.Tariff;
import com.epam.spring.boot.cargodeliverysystem.repository.LocationRepository;
import com.epam.spring.boot.cargodeliverysystem.repository.TariffRepository;
import com.epam.spring.boot.cargodeliverysystem.service.impl.GeneralInfoServiceImpl;
import com.epam.spring.boot.cargodeliverysystem.test.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class GeneralInfoServiceImplTest {

    @InjectMocks
    private GeneralInfoServiceImpl generalInfoService;

    @Spy
    private final TariffMapper tariffMapper = new TariffMapperImpl();
    @Spy
    private final LocationMapper locationMapper = new LocationMapperImpl();
    @Mock
    private TariffRepository tariffRepository;
    @Mock
    private LocationRepository locationRepository;

    @Test
    void getInfoAboutTariffsWithNameTest(){
        List<Tariff> ukTariffList = TestDataUtil.createUkTariffList();
        List<TariffDto> expectedTariffDtoList = TestDataUtil.createTariffListDto();

        when(tariffRepository.findAllByLanguage_LanguageName(eq(TEST_LANGUAGE_NAME), any())).thenReturn(ukTariffList);

        List<TariffDto> tariffDtoList = generalInfoService.getInfoAboutTariffsWithLanguageName(TEST_LANGUAGE_NAME, 0, "id");
        assertThat(tariffDtoList, equalTo(expectedTariffDtoList));
    }

    @Test
    void getInfoAboutLocationsTest(){
        List<Location> locationList = TestDataUtil.createLocationList();
        List<LocationDto> expectedLocationDtoList = TestDataUtil.createLocationDtoList();
        Page<Location> page = new PageImpl<>(locationList);

        when(locationRepository.findAll(any(Pageable.class))).thenReturn(page);

        List<LocationDto> locationDtoList = generalInfoService.getInfoAboutLocations(0, "id");
        assertThat(locationDtoList, equalTo(expectedLocationDtoList));
    }

}
