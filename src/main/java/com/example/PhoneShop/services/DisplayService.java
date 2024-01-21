package com.example.PhoneShop.services;

import com.example.PhoneShop.models.Display;
import com.example.PhoneShop.repository.DisplayRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class DisplayService {

    private DisplayRepository displayRepository;

    @Autowired
    public void setDisplayRepository(DisplayRepository displayRepository) {
        this.displayRepository = displayRepository;
    }

    public Iterable<Display> getAllDisplays() {
        return displayRepository.findAll();
    }

    public Display getDisplayById(Long id) {
        Optional<Display> display = displayRepository.findById(id);

        return display.orElse(null);
    }

    public Display getDisplayByName(String name) {
        Optional<Display> display = displayRepository.findByName(name);

        return display.orElse(null);
    }

    public void saveDisplay(Display display) {
        displayRepository.save(display);
    }

}
