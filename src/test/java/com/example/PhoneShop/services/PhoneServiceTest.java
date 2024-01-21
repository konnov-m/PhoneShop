package com.example.PhoneShop.services;

import com.example.PhoneShop.models.Company;
import com.example.PhoneShop.models.Display;
import com.example.PhoneShop.models.Phone;
import com.example.PhoneShop.repository.CompanyRepository;
import com.example.PhoneShop.repository.DisplayRepository;
import com.example.PhoneShop.repository.PhoneRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PhoneServiceTest {

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private DisplayRepository displayRepository;

    private PhoneService phoneService;

    private CompanyService companyService;

    private DisplayService displayService;


    @BeforeEach
    void beforeEach() {
        phoneService = new PhoneService();
        companyService = new CompanyService();
        displayService = new DisplayService();

        phoneService.setPhoneRepository(phoneRepository);
        companyService.setPhoneService(phoneService);
        companyService.setCompanyRepository(companyRepository);
        displayService.setDisplayRepository(displayRepository);
    }



    @Test
    void savePhone() {
        String name = "IPhone 14";
        Phone phone = new Phone();

        Company company = companyService.getCompanyByTitle("Apple");

        phone.setName(name);
        phone.setCompany(company);
        phone.setDescription("Iphone 14 description");
        phone.setDisplay(displayService.getDisplayById(1L));


        phoneService.savePhone(phone);

        Phone savedPhone = phoneRepository.findByName(name).get();

        assertEquals(savedPhone.getName(), phone.getName());
        assertEquals(savedPhone.getCompany(), phone.getCompany());
        assertEquals(savedPhone.getDescription(), phone.getDescription());
    }

    @Test
    void savePhoneWrong() {
        String name = "IPhone 14";
        Phone phone = new Phone();

        Company company = companyService.getCompanyByTitle("Apple");

        phone.setName(name);
        phone.setCompany(company);
        phone.setDescription("Iphone 14 description");
        phone.setDisplay(new Display());
        phone.setDisplay(displayService.getDisplayById(1L));


        phoneService.savePhone(phone);

        Phone savedPhone = phoneRepository.findByName(name).get();

        assertNotEquals(savedPhone.getName(), name.toLowerCase()); // Lower case
        assertEquals(savedPhone.getCompany(), phone.getCompany());
        assertEquals(savedPhone.getDescription(), phone.getDescription());
    }

    @Test
    void deletePhone() {
        Phone phone = phoneRepository.findByName("Pixel 8").get();


        phoneService.deletePhoneById(phone.getId());

        Assertions.assertFalse(phoneRepository.findByName("Pixel 8").isPresent());
    }


    @Test
    void updateCompany() {
        Phone phone = phoneRepository.findByName("Pixel 8").get();
        Company company = companyService.getCompanyByTitle("Apple");

        phone.setCompany(company);

        phoneService.savePhone(phone);


        assertEquals(phoneRepository.findByName("Pixel 8").get().getCompany(), company);

    }

    @Test
    void updateDescription() {
        String description = "description";
        Phone phone = phoneRepository.findByName("Pixel 8").get();

        phone.setDescription(description);

        phoneService.savePhone(phone);


        assertEquals(phoneRepository.findByName("Pixel 8").get().getDescription(), description);

    }

    @Test
    void updateName() {
        String name = "Pixel 7";
        Phone phone = phoneRepository.findByName("Pixel 8").get();

        phone.setName(name);

        phoneService.savePhone(phone);


        assertEquals(phoneRepository.findByName("Pixel 7").get().getName(), name);

    }
}