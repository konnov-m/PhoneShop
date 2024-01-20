package com.example.PhoneShop.services;

import com.example.PhoneShop.models.Company;
import com.example.PhoneShop.models.Phone;
import com.example.PhoneShop.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CompanyService {

    private CompanyRepository companyRepository;

    private PhoneService phoneService;

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Autowired
    public void setPhoneService(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    public void deleteCompanyById(Long id) {
        Optional<Company> company = companyRepository.findById(id);

        if (company.isEmpty()) {
            log.error("Tried to delete company which don't exists. id=" + id);
            return;
        }

        for (Phone phone: company.get().getPhones()) {
            phoneService.deletePhoneById(phone.getId());
        }

        companyRepository.deleteById(id);
    }

    public Company getCompanyById(Long id) throws IllegalArgumentException {
        Optional<Company> company = companyRepository.findById(id);

        return company.orElse(null);
    }

    public Iterable<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

}
