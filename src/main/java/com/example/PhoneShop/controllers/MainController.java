package com.example.PhoneShop.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер с главной страницой.
 * @author Коннов Михаил
 */
@Controller
public class MainController {

    @GetMapping("/")
    public String getIndex() {
        return "main/index";
    }
}
