package com.example.PhoneShop.controllers;

import com.example.PhoneShop.models.Company;
import com.example.PhoneShop.services.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для класса {@link Company}.
 * @author Коннов Михаил
 */
@Controller
@RequestMapping("/company")
@Slf4j
public class CompanyController {

    private CompanyService companyService;

    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/{id}")
    public String getCompany(@PathVariable("id") Long id, Model model) {
        log.info("Get company with id=" + id);
        Company company = companyService.getCompanyById(id);

        if (company == null) {
            log.error("There is no company with id=" + id);
            return "error/404";
        }

        model.addAttribute("company", company);
        return "company/get";
    }

    @GetMapping({"", "/"})
    public String getAll(Model model) {
        log.info("Get all companies");
        Iterable<Company> companies = companyService.getAllCompanies();

        model.addAttribute("companies", companies);

        return "company/getAll";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        companyService.deleteCompanyById(id);

        return "redirect:/company";
    }

}
