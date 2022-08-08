package com.epam.spring.boot.cargodeliverysystem.repository;

import com.epam.spring.boot.cargodeliverysystem.model.DeliveryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryTypeRepository extends JpaRepository<DeliveryType, Long> {

    @Query("select dt from DeliveryType dt where dt.typeName = ?1")
    Optional<DeliveryType> findByTypeName(String typeName);

    List<DeliveryType> findAllByLanguage_LanguageName(String languageName);
}
