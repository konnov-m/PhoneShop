package com.example.PhoneShop.services;

import com.example.PhoneShop.models.Display;
import com.example.PhoneShop.repository.DisplayRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
