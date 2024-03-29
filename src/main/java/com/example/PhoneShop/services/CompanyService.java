package com.example.PhoneShop.services;

import com.example.PhoneShop.models.Company;
import com.example.PhoneShop.models.Phone;
import com.example.PhoneShop.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


/**
 * <p>Сервис по работе с {@link Company}.<p/>
 * <br>Удаление {@link CompanyService#deleteCompanyById(Long)}</br>
 * <br>Получить экземпляр {@link CompanyService#getCompanyById(Long)} </br>
 * <br>Получить все экземпляры {@link CompanyService#getAllCompanies()}</br>
 * <br>Получить экземпляр по полю {@link Company#getTitle()}. Функция {@link CompanyService#getCompanyByTitle(String)} </br>
 * <br>Сохранить и обновить экземпляр. {@link CompanyService#saveCompany(Company)}</br>
 * <br>Получтиь множество всех названий компаний. {@link CompanyService#getAllTitles()}</br>
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
        log.info("deleteCompanyById() with parameter id = " + id);
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
        log.info("getCompanyById() with parameter id = " + id);
        Optional<Company> company = companyRepository.findById(id);

        return company.orElse(null);
    }

    public Iterable<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyByTitle(String title) {
        log.info("getCompanyByTitle() with parameter title = " + title);
        Optional<Company> company = companyRepository.findByTitle(title);

        return company.orElse(null);
    }

    public void saveCompany(Company company) {
        log.info("saveCompany() with parameter company = " + company);
        companyRepository.save(company);
    }

    /**
     * Получить множество всех названий компаний
     * @return Set&lt;String&gt;
     */
    public Set<String> getAllTitles() {
        Set<String> set = new HashSet<>();

        Iterable<Company> companies = companyRepository.findAll();

        for (Company company: companies) {
            set.add(company.getTitle());
        }

        return set;
    }

}
