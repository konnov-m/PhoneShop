package com.example.PhoneShop.controllers;

import com.example.PhoneShop.models.Company;
import com.example.PhoneShop.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * Контроллер для класса {@link Company}.
 * @author Коннов Михаил
 */
@Controller
@RequestMapping("/company")
@Slf4j
public class CompanyController {

    private CompanyRepository companyRepository;

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    @GetMapping("/{id}")
    public String getCompany(@PathVariable("id") Long id, Model model) {
        log.info("Get company with id=" + id);
        Optional<Company> company = companyRepository.findById(id);

        if (company.isEmpty()) {
            log.info("There is no company with id=" + id);
            return "error/404";
        }
        model.addAttribute("company", company.get());
        return "company/get";
    }

    @GetMapping("/")
    public String getAll(Model model) {
        log.info("Get all companies");
        Iterable<Company> companies = companyRepository.findAll();

        model.addAttribute("companies", companies);

        return "company/getAll";
    }

}
