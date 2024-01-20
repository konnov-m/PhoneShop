package com.example.PhoneShop.controllers;

import com.example.PhoneShop.models.Phone;
import com.example.PhoneShop.services.PhoneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Контроллер для класса {@link Phone}.
 * @author Коннов Михаил
 */
@Controller
@RequestMapping("/phone")
@Slf4j
public class PhoneController {

    private PhoneService phoneService;

    @Autowired
    public void setPhoneService(PhoneService phoneService) {
        this.phoneService = phoneService;
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

}
