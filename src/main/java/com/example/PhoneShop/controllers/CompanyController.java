package com.example.PhoneShop.controllers;

import com.example.PhoneShop.models.Company;
import com.example.PhoneShop.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/company")
public class CompanyController {

    private CompanyRepository companyRepository;

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    @GetMapping("/{id}")
    public String getCompany(@PathVariable("id") Long id, Model model) {
        Optional<Company> company = companyRepository.findById(id);

        if (company.isEmpty()) {
            return "error/404";
        }
        model.addAttribute("company", company.get());
        return "company/get";
    }

}
