package com.example.PhoneShop.services;

import com.example.PhoneShop.models.Company;
import com.example.PhoneShop.repository.CompanyRepository;
import com.example.PhoneShop.repository.PhoneRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CompanyServiceTest {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    PhoneRepository phoneRepository;

    CompanyService companyService;

    @BeforeEach
    void beforeEach() {
        companyService = new CompanyService();
        PhoneService phoneService = new PhoneService();

        phoneService.setPhoneRepository(phoneRepository);

        companyService.setPhoneService(phoneService);
        companyService.setCompanyRepository(companyRepository);
    }

    @Test
    void saveCompany() {
        Company company = new Company();

        company.setTitle("Nothing");

        companyService.saveCompany(company);

        Company savedCompany = companyRepository.findByTitle(company.getTitle()).orElse(null);

        Assertions.assertEquals(savedCompany.getTitle(), company.getTitle());
    }

    @Test
    void saveCompanyWrong() {
        Company company = new Company();

        company.setTitle("Nothing");

        companyService.saveCompany(company);

        Company savedCompany = companyRepository.findByTitle(company.getTitle()).orElse(null);

        Assertions.assertNotEquals("nothing", company.getTitle());
    }


    @Test
    void deleteCompany() {
        Company savedCompany = companyRepository.findByTitle("Google").orElse(null);

        companyService.deleteCompanyById(savedCompany.getId());

        Assertions.assertFalse(companyRepository.findById(savedCompany.getId()).isPresent());
    }

    @Test
    void updateCompany() {
        Company company = companyRepository.findByTitle("Google").orElse(null);

        company.setTitle("google");

        companyService.saveCompany(company);

        Assertions.assertEquals(company, companyRepository.findByTitle("google").orElse(null));
    }

    @Test
    void updateCompanyWrong() {
        Company company = companyRepository.findByTitle("Google").orElse(null);

        company.setTitle("google");

        companyService.saveCompany(company);

        Assertions.assertNotEquals(company, companyRepository.findByTitle("Google").orElse(null));
    }


}