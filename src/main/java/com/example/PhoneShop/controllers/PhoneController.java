package com.example.PhoneShop.controllers;

import com.example.PhoneShop.models.Company;
import com.example.PhoneShop.models.Phone;
import com.example.PhoneShop.services.CompanyService;
import com.example.PhoneShop.services.PhoneService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Контроллер для класса {@link Phone}.
 * @author Коннов Михаил
 */
@Controller
@RequestMapping("/phone")
@Slf4j
public class PhoneController {

    private PhoneService phoneService;

    private CompanyService companyService;

    @Autowired
    public void setPhoneService(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/{id}")
    public String getPhone(@PathVariable("id") Long id, Model model) {
        log.info("Get phone with id=" + id);
        Phone phone = phoneService.getPhoneById(id);

        if (phone == null) {
            log.error("There is no phone with id=" + id);
            return "error/404";
        }

        model.addAttribute("phone", phone);
        return "phone/get";
    }

    @GetMapping({"", "/"})
    public String getAll(Model model) {
        log.info("Get all phones");
        Iterable<Phone> phones = phoneService.getAllPhones();

        model.addAttribute("phones", phones);

        return "phone/getAll";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        phoneService.deletePhoneById(id);

        return "redirect:/phone";
    }

    @GetMapping("/create")
    public String createGet(@RequestParam(value = "nameExist", required = false) String nameExist,
                            @ModelAttribute("phone") Phone phone, Model model) {
        log.info("createGet(). Get phone = " + phone);

        model.addAttribute("companies", companyService.getAllCompanies());
        model.addAttribute("nameExist", nameExist != null);

        return "phone/create";
    }


    @PostMapping("/create")
    public String createPost(@ModelAttribute("phone") @Valid Phone phone,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("bindingResult.hasErrors(). Phone to redirect = " + phone);
            return "phone/create";
        }

        if (phoneService.getPhoneByName(phone.getName()) != null) {
            redirectAttributes.addFlashAttribute("phone", phone);
            log.info("Phone with this name exist. Phone to redirect = " + phone);
            return "redirect:/phone/create?nameExist";
        }

        phoneService.savePhone(phone);

        return "redirect:/phone";
    }

    @GetMapping("/update/{id}")
    public String updateGet(@RequestParam(value = "nameExist", required = false) String nameExist,
                            @ModelAttribute("phone") Phone phone, @PathVariable("id") Long id,
                            Model model) {

        if (phone.getName() == null) {
            phone = phoneService.getPhoneById(id);
            if (phone == null) {
                log.error("There is no phone with id=" + id);
                return "error/404";
            }

        }

        Iterable<Company> it = companyService.getAllCompanies();
        List<Company> companies = new ArrayList<>();

        it.forEach(companies::add);
        companies.remove(phone.getCompany());


        model.addAttribute("phone", phone);
        model.addAttribute("companies", companies);
        model.addAttribute("nameExist", nameExist != null);

        return "phone/update";
    }

    @PostMapping("/update/{id}")
    public String updatePost(@ModelAttribute("phone") @Valid Phone phone, BindingResult bindingResult,
                             @PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("bindingResult.hasErrors(). Phone to redirect = " + phone);
            return "phone/update";
        }

        Phone phoneByName = phoneService.getPhoneByName(phone.getName());

        if (phoneByName != null && !Objects.equals(phoneByName.getId(), phone.getId())) {
            redirectAttributes.addFlashAttribute("phone", phone);
            log.info("Phone with this name exist. Phone to redirect = " + phone);
            return "redirect:/phone/update/" + id + "?nameExist";
        }

        phoneService.savePhone(phone);

        return "redirect:/phone/" + id;
    }


}
