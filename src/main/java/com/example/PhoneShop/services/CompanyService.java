package com.example.PhoneShop.services;

import com.example.PhoneShop.models.Company;
import com.example.PhoneShop.models.Phone;
import com.example.PhoneShop.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * <p>Сервис по работе с {@link Company}.<p/>
 * <br>Удаление {@link CompanyService#deleteCompanyById(Long)}</br>
 * <br>Получить экземпляр {@link CompanyService#getCompanyById(Long)} </br>
 * <br>Получить все экземпляры {@link CompanyService#getAllCompanies()}</br>
 * <br>Получить экземпляр по полю {@link Company#getTitle()}. Функция {@link CompanyService#getCompanyByTitle(String)} </br>
 * <br>Сохранить и обновить экземпляр. {@link CompanyService#saveCompany(Company)}</br>
 * <br></br>
 */
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

    public Company getCompanyByTitle(String title) {
        Optional<Company> company = companyRepository.findByTitle(title);

        return company.orElse(null);
    }

    public void saveCompany(Company company) {
        companyRepository.save(company);
    }

}
