package com.example.PhoneShop.controllers;

import com.example.PhoneShop.models.Display;
import com.example.PhoneShop.services.DisplayService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
@RequestMapping("/display")
@Slf4j
public class DisplayController {

    private DisplayService displayService;

    @Autowired
    public void setDisplayService(DisplayService displayService) {
        this.displayService = displayService;
    }

    @GetMapping("/create")
    public String createGet(@RequestParam(value = "nameExist", required = false) String nameExist,
                            @ModelAttribute("display") Display display, Model model) {

        model.addAttribute("nameExist", nameExist != null);

        return "display/create";
    }

    @PostMapping("/create")
    public String createPost (@ModelAttribute("display") @Valid Display display,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("bindingResult.hasErrors(). Display to redirect = " + display);
            return "display/create";
        }

        if (displayService.getDisplayByName(display.getName()) != null) {
            redirectAttributes.addFlashAttribute("display", display);
            log.info("Display with this name exist. Display to redirect = " + display);
            return "redirect:/display/create?nameExist";
        }

        displayService.saveDisplay(display);

        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updateGet(@RequestParam(value = "nameExist", required = false) String nameExist,
                            @ModelAttribute("display") Display display, @PathVariable("id") Long id,
                            Model model) {

        if (display.getName() == null) {
            display = displayService.getDisplayById(id);
            if (display == null) {
                log.error("There is no phone with id=" + id);
                return "error/404";
            }
        }

        model.addAttribute("display", display);
        model.addAttribute("nameExist", nameExist != null);

        return "display/update";
    }

    @PostMapping("/update/{id}")
    public String updatePost(@ModelAttribute("display") @Valid Display display, BindingResult bindingResult,
                             @PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("bindingResult.hasErrors(). Display to redirect = " + display);
            return "display/update";
        }

        Display displayByName = displayService.getDisplayByName(display.getName());

        if (displayByName != null && !Objects.equals(displayByName.getId(), display.getId())) {
            redirectAttributes.addFlashAttribute("display", display);
            log.info("Display with this name exist. Display to redirect = " + display);
            return "redirect:/display/update/" + id + "?nameExist";
        }

        displayService.saveDisplay(display);

        return "redirect:/";
    }
}
