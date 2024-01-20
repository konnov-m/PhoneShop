package com.example.PhoneShop.controllers;

import com.example.PhoneShop.models.Company;
import com.example.PhoneShop.services.CompanyService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

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

    @GetMapping("/create")
    public String createGet(@RequestParam(value = "titleExist", required = false) String titleExist,
            @ModelAttribute("company") Company company,
            Model model) {
        model.addAttribute("titleExist", titleExist != null);

        return "company/create";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute("company") @Valid Company company, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "company/create";
        }

        if (companyService.getCompanyByTitle(company.getTitle()) != null) {
            redirectAttributes.addFlashAttribute("company", company);
            log.info("Company with this title exist. Phone to redirect = " + company);
            return "redirect:/company/create?titleExist";
        }

        companyService.saveCompany(company);

        return "redirect:/company";
    }

    @GetMapping("/update/{id}")
    public String updateGet(@RequestParam(value = "titleExist", required = false) String titleExist,
                            @ModelAttribute("company") Company company, @PathVariable("id") Long id,
                            Model model) {
        model.addAttribute("titleExist", titleExist != null);
        if (company.getTitle() == null) {
            company = companyService.getCompanyById(id);
            model.addAttribute("company", company);
        }


        return "company/update";
    }


    @PostMapping("/update/{id}")
    public String updatePost(@ModelAttribute("company") @Valid Company company, BindingResult bindingResult,
                             @PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "company/update";
        }

        Company companyTitle = companyService.getCompanyByTitle(company.getTitle());

        if (companyTitle != null && Objects.equals(companyTitle.getId(), company.getId())) {
            redirectAttributes.addFlashAttribute("company", company);
            log.info("Company with this title exist. Phone to redirect = " + company);
            return "redirect:/company/update/" + id + "?titleExist";
        }

        companyService.saveCompany(company);

        return "redirect:/company";
    }

}
