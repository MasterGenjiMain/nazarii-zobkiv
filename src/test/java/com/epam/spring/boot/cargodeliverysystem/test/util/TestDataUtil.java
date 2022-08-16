package com.epam.spring.boot.cargodeliverysystem.test.util;

import com.epam.spring.boot.cargodeliverysystem.dto.*;
import com.epam.spring.boot.cargodeliverysystem.model.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDataUtil {

    public static final String TEST_USERNAME = "TestUsername";
    public static final String TEST_EMAIL = "Test@email.com";
    public static final String TEST_PASSWORD = "testPassword";
    public static final String TEST_LANGUAGE_NAME = "uk";

    public static User createUser(){
        return User.builder()
                .username(TEST_USERNAME)
                .email(TEST_EMAIL)
                .password(TEST_PASSWORD)
                .createTime(new Date(1212121212121L))
                .role(createRole())
                .build();
    }

    public static UserDto createUserDto(){
        return UserDto.builder()
                .username(TEST_USERNAME)
                .email(TEST_EMAIL)
                .password(TEST_PASSWORD)
                .repeatPassword(TEST_PASSWORD)
                .createTime(new Date(1212121212121L))
                .role(createRoleDto())
                .build();
    }

    public static Role createRole() {
        return Role.builder()
                .id(1L)
                .roleName("TestRoleName")
                .build();
    }

    public static RoleDto createRoleDto() {
        return RoleDto.builder()
                .id(1L)
                .roleName("TestRoleName")
                .build();
    }

    public static List<Tariff> createUkTariffList() {
        List<Tariff> tariffList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Tariff tariff = createTariff();
            tariff.setTariffName("TestTariffName" + i);
            tariffList.add(tariff);
        }
        return tariffList;
    }

    public static Tariff createTariff(){
        return Tariff.builder()
                .tariffName("TestTariffName")
                .tariffPrice(100)
                .tariffInfo("TestInfo")
                .language(createLanguage())
                .build();
    }

    public static Language createLanguage() {
        return Language.builder()
                .languageName("uk")
                .build();
    }

    public static List<TariffDto> createTariffListDto() {
        List<TariffDto> tariffDtoList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            TariffDto tariffDto = createTariffDto();
            tariffDto.setTariffName("TestTariffName" + i);
            tariffDtoList.add(tariffDto);
        }
        return tariffDtoList;
    }

    public static TariffDto createTariffDto(){
        return TariffDto.builder()
                .tariffName("TestTariffName")
                .tariffPrice(100)
                .tariffInfo("TestInfo")
                .language(createLanguageDto())
                .build();
    }

    private static LanguageDto createLanguageDto() {
        return LanguageDto.builder()
                .languageName(TEST_LANGUAGE_NAME)
                .build();
    }

    public static List<Location> createLocationList() {
        List<Location> locationList = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            Location location = createLocation();
            location.setLocationName("testLocationName" + i);
            locationList.add(location);
        }
        return locationList;
    }

    public static Location createLocation() {
        return Location.builder()
                .locationName("testLocationName")
                .city(createCity())
                .activeStatus(createActiveStatus())
                .build();
    }

    private static ActiveStatus createActiveStatus() {
        return ActiveStatus.builder()
                .activeStatusName("testActiveStatusName")
                .build();
    }

    private static City createCity() {
        return City.builder()
                .cityName("testCityName")
                .build();
    }

    public static List<LocationDto> createLocationDtoList() {
        List<LocationDto> locationDtoList = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            LocationDto locationDto = createLocationDto();
            locationDto.setLocationName("testLocationName" + i);
            locationDtoList.add(locationDto);
        }
        return locationDtoList;
    }

    public static LocationDto createLocationDto() {
        return LocationDto.builder()
                .locationName("testLocationName")
                .city(createCityDto())
                .activeStatus(createActiveStatusDto())
                .build();
    }

    private static ActiveStatusDto createActiveStatusDto() {
        return ActiveStatusDto.builder()
                .activeStatusName("testActiveStatusName")
                .build();
    }

    private static CityDto createCityDto() {
        return CityDto.builder()
                .cityName("testCityName")
                .build();
    }

    public static List<DeliveryOrder> createDeliveryOrderList() {
        List<DeliveryOrder> deliveryOrderList = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            DeliveryOrder deliveryOrder = createDeliveryOrder();
            deliveryOrder.setCargoName("testName" + i);
            deliveryOrderList.add(deliveryOrder);
        }
        return deliveryOrderList;
    }

    private static DeliveryOrder createDeliveryOrder() {
        return DeliveryOrder.builder()
                .locationFrom(createLocation())
                .locationTo(createLocation())
                .cargoName("testName")
                .cargoDescription("testDesc")
                .address("testAddress")
                .deliveryType(createDeliveryType())
                .weight(100)
                .volume(100)
                .receivingDate(null)
                .tariff(createDeliveryTariff())
                .build();
    }

    private static Tariff createDeliveryTariff() {
        return Tariff.builder()
                .tariffName("testTariffName")
                .tariffInfo("testTariffInfo")
                .language(createLanguage())
                .build();
    }

    private static TariffDto createDeliveryTariffDto() {
        return TariffDto.builder()
                .tariffName("testTariffName")
                .tariffInfo("testTariffInfo")
                .language(createLanguageDto())
                .build();
    }

    private static DeliveryType createDeliveryType() {
        return DeliveryType.builder()
                .typeName("testTypeName")
                .language(createLanguage())
                .build();
    }

    private static DeliveryTypeDto createDeliveryTypeDto() {
        return DeliveryTypeDto.builder()
                .typeName("testTypeName")
                .language(createLanguageDto())
                .build();
    }

    public static DeliveryOrderDto createDeliveryOrderDto() {
        return DeliveryOrderDto.builder()
                .locationFrom(createLocationDto())
                .locationTo(createLocationDto())
                .cargoName("testName")
                .cargoDescription("testDesc")
                .address("testAddress")
                .deliveryType(createDeliveryTypeDto())
                .weight(100)
                .volume(100)
                .receivingDate(null)
                .tariff(createDeliveryTariffDto())
                .build();
    }

    public static List<DeliveryOrderDto> createDeliveryOrderDtoList() {
        List<DeliveryOrderDto> deliveryOrderDtoList = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            DeliveryOrderDto deliveryOrderDto = createDeliveryOrderDto();
            deliveryOrderDto.setCargoName("testName" + i);
            deliveryOrderDtoList.add(deliveryOrderDto);
        }
        return deliveryOrderDtoList;
    }

    public static List<Receipt> createReceiptList() {
        List<Receipt> receiptList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Receipt receipt = createReceipt();
            receiptList.add(receipt);
        }
        return receiptList;
    }

    public static Receipt createReceipt(){
        return Receipt.builder()
                .id(1L)
                .user(createUser())
                .manager(createUser())
                .price(100)
                .receiptStatus(createReceiptStatus())
                .deliveryOrder(createDeliveryOrder())
                .build();
    }

    public static ReceiptStatus createReceiptStatus() {
        return ReceiptStatus.builder()
                .id(1L)
                .statusName("testReceiptStatus")
                .build();
    }

    public static List<ReceiptDto> createReceiptDtoList() {
        List<ReceiptDto> receiptList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ReceiptDto receiptDto = createReceiptDto();
            receiptList.add(receiptDto);
        }
        return receiptList;
    }

    public static ReceiptDto createReceiptDto() {
        return ReceiptDto.builder()
                .id(1L)
                .user(createUserDto())
                .manager(createUserDto())
                .price(100)
                .receiptStatus(createReceiptStatusDto())
                .deliveryOrder(createDeliveryOrderDto())
                .build();
    }

    public static ReceiptStatusDto createReceiptStatusDto() {
        return ReceiptStatusDto.builder()
                .statusName("testStatusName")
                .build();
    }
}
