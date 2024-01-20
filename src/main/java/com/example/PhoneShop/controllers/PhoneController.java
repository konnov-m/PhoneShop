package com.example.PhoneShop.controllers;

import com.example.PhoneShop.models.Phone;
import com.example.PhoneShop.repository.PhoneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * Контроллер для класса {@link Phone}.
 * @author Коннов Михаил
 */
@Controller
@RequestMapping("/phone")
@Slf4j
public class PhoneController {

    private PhoneRepository phoneRepository;

    @Autowired
    public void setPhoneRepository(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @GetMapping("/{id}")
    public String getPhone(@PathVariable("id") Long id, Model model) {
        log.info("Get phone with id=" + id);
        Optional<Phone> phone = phoneRepository.findById(id);

        if (phone.isEmpty()) {
            log.info("There is no phone with id=" + id);
            return "error/404";
        }
        model.addAttribute("phone", phone.get());
        return "phone/get";
    }

    @GetMapping({"", "/"})
    public String getAll(Model model) {
        log.info("Get all phones");
        Iterable<Phone> phones = phoneRepository.findAll();

        model.addAttribute("phones", phones);

        return "phone/getAll";
    }

}
