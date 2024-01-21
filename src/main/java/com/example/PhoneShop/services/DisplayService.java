package com.example.PhoneShop.services;

import com.example.PhoneShop.models.Display;
import com.example.PhoneShop.repository.DisplayRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * <p>Сервис по работе с {@link Display}.<p/>
 * <br>Получить все экземпляры {@link DisplayService#getAllDisplays}</br>
 * <br>Получить по {@link Display#getId()}. Функция {@link DisplayService#getDisplayById}</br>
 * <br>Получить по {@link Display#getName()}. Функция {@link DisplayService#getDisplayByName}</br>
 * <br>Сохранить и обновить {@link DisplayService#saveDisplay}</br>
 * <br>Получить все существующие {@link Display#getTypeMatrix()} ()}. Функция {@link DisplayService#getAllTypeMatrix}</br>
 * <br>Получить все существующие {@link Display#getRate()} ()}. Функция {@link DisplayService#getAllRates}</br>
 */
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
        log.info("getDisplayById() with parameter id = " + id);
        Optional<Display> display = displayRepository.findById(id);

        return display.orElse(null);
    }

    public Display getDisplayByName(String name) {
        log.info("getDisplayByName() with parameter name = " + name);
        Optional<Display> display = displayRepository.findByName(name);

        return display.orElse(null);
    }

    public void saveDisplay(Display display) {
        log.info("saveDisplay() with parameter display = " + display);
        displayRepository.save(display);
    }

    public Set<String> getAllTypeMatrix() {
        Set<String> set = new HashSet<>();

        Iterable<Display> displays = displayRepository.findAll();

        for (Display d: displays) {
            set.add(d.getTypeMatrix());
        }
        return set;
    }

    public Set<Integer> getAllRates() {
        Set<Integer> set = new HashSet<>();

        Iterable<Display> displays = displayRepository.findAll();

        for (Display d: displays) {
            set.add(d.getRate());
        }
        return set;
    }

}
