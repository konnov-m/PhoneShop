package com.example.PhoneShop.services;

import com.example.PhoneShop.models.Company;
import com.example.PhoneShop.repository.CompanyRepository;
import com.example.PhoneShop.repository.PhoneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

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

        assertEquals(savedCompany.getTitle(), company.getTitle());
    }

    @Test
    void saveCompanyWrong() {
        Company company = new Company();

        company.setTitle("Nothing");

        companyService.saveCompany(company);

        Company savedCompany = companyRepository.findByTitle(company.getTitle()).orElse(null);

        assertNotEquals("nothing", company.getTitle());
    }


    @Test
    void deleteCompany() {
        Company savedCompany = companyRepository.findByTitle("Google").orElse(null);

        companyService.deleteCompanyById(savedCompany.getId());

        assertFalse(companyRepository.findById(savedCompany.getId()).isPresent());
    }

    @Test
    void updateCompany() {
        Company company = companyRepository.findByTitle("Google").orElse(null);

        company.setTitle("google");

        companyService.saveCompany(company);

        assertEquals(company, companyRepository.findByTitle("google").orElse(null));
    }

    @Test
    void updateCompanyWrong() {
        Company company = companyRepository.findByTitle("Google").orElse(null);

        company.setTitle("google");

        companyService.saveCompany(company);

        assertNotEquals(company, companyRepository.findByTitle("Google").orElse(null));
    }

    @Test
    void getGoogle() {
        String title = "Google";

        Company companyRep = companyRepository.findByTitle(title).orElse(null);
        Company companySer = companyService.getCompanyByTitle(title);
        assertEquals(companyRep, companySer);
    }

    @Test
    void getApple() {
        String title = "Apple";

        Company companyRep = companyRepository.findByTitle(title).orElse(null);
        Company companySer = companyService.getCompanyByTitle(title);
        assertEquals(companyRep, companySer);
    }

    // There is no "Nothing" in db
    @Test
    void getNothing() {
        String title = "Nothing";

        Company companyRep = companyRepository.findByTitle(title).orElse(null);
        Company companySer = companyService.getCompanyByTitle(title);
        assertEquals(companyRep, companySer);
        assertNull(companyRep);
    }


}