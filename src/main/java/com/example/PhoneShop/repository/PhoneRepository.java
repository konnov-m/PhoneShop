package com.example.PhoneShop.repository;

import com.example.PhoneShop.models.Phone;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PhoneRepository extends CrudRepository<Phone, Long> {
    Optional<Phone> findByName(String name);

    Iterable<Phone> findByDisplay_TypeMatrix(String typeMatrix);

}
