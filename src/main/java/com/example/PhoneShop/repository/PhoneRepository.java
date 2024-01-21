package com.example.PhoneShop.repository;

import com.example.PhoneShop.models.Company;
import com.example.PhoneShop.models.Phone;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PhoneRepository extends CrudRepository<Phone, Long> {
    Optional<Phone> findByName(String name);

    Optional<Phone> findByCompany(Company company);

    Iterable<Phone> findByDisplay_TypeMatrix(String typeMatrix);

    Iterable<Phone> findByCompany_Title(String title);
}
