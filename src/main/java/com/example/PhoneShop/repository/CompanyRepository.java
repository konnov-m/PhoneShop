package com.example.PhoneShop.repository;

import com.example.PhoneShop.models.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CompanyRepository extends CrudRepository<Company, Long> {
    Optional<Company> findByTitle(String title);
}
