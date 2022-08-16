package com.epam.spring.boot.cargodeliverysystem.controller;

import com.epam.spring.boot.cargodeliverysystem.dto.LocationDto;
import com.epam.spring.boot.cargodeliverysystem.dto.TariffDto;
import com.epam.spring.boot.cargodeliverysystem.service.GeneralInfoService;
import com.epam.spring.boot.cargodeliverysystem.test.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GeneralInfoController.class)
public class GeneralInfoControllerTest {

    @MockBean
    private GeneralInfoService generalInfoService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getInfoAboutTariffsWithLanguageName() throws Exception {
        List<TariffDto> tariffDtoList = TestDataUtil.createTariffListDto();
        String languageName = "uk";
        when(generalInfoService.getInfoAboutTariffsWithLanguageName(languageName, 0, "id")).thenReturn(tariffDtoList);

        mockMvc.perform(get("/generalInfo/tariffInfo/" + languageName + "?pageNum=0&sortBy=id"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].tariffName").value(tariffDtoList.get(0).getTariffName()))
                .andExpect(jsonPath("$[0].tariffPrice").value(tariffDtoList.get(0).getTariffPrice()))
                .andExpect(jsonPath("$[0].tariffInfo").value(tariffDtoList.get(0).getTariffInfo()))
                .andExpect(jsonPath("$[0].language.languageName").value(languageName));
    }

    @Test
    void getInfoAboutTariffsWithShouldReturnCorrectValuesLanguageName() throws Exception {
        List<TariffDto> tariffDtoList = TestDataUtil.createTariffListDto();
        String languageName = "en";
        when(generalInfoService.getInfoAboutTariffsWithLanguageName(languageName, 0, "id")).thenReturn(tariffDtoList);

        mockMvc.perform(get("/generalInfo/tariffInfo/" + languageName + "?pageNum=0&sortBy=id"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[2].tariffName").value(tariffDtoList.get(2).getTariffName()))
                .andExpect(jsonPath("$[2].tariffPrice").value(tariffDtoList.get(2).getTariffPrice()))
                .andExpect(jsonPath("$[2].tariffInfo").value(tariffDtoList.get(2).getTariffInfo()));
    }

    @Test
    void getLocationsInfoTest() throws Exception {
        List<LocationDto> locationDtoList = TestDataUtil.createLocationDtoList();

        when(generalInfoService.getInfoAboutLocations(0, "id")).thenReturn(locationDtoList);

        mockMvc.perform(get("/generalInfo/locationInfo/?pageNum=0&sortBy=id"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].locationName").value(locationDtoList.get(0).getLocationName()));
    }

    @Test
    void getLocationsInfoShouldReturnCorrectValuesTest() throws Exception {
        List<LocationDto> locationDtoList = TestDataUtil.createLocationDtoList();

        when(generalInfoService.getInfoAboutLocations(0, "id")).thenReturn(locationDtoList);

        mockMvc.perform(get("/generalInfo/locationInfo/?pageNum=0&sortBy=id"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].locationName").value(locationDtoList.get(0).getLocationName()))
                .andExpect(jsonPath("$[0].city.cityName").value(locationDtoList.get(0).getCity().getCityName()))
                .andExpect(jsonPath("$[0].activeStatus.activeStatusName").value(locationDtoList.get(0).getActiveStatus().getActiveStatusName()));
    }
}
