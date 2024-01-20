package com.example.PhoneShop.services;

import com.example.PhoneShop.models.Phone;
import com.example.PhoneShop.repository.PhoneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>Сервис по работе с {@link Phone}.<p/>
 * <br>Удаление {@link PhoneService#deletePhoneById(Long)} (Long)}</br>
 * <br>Получить экземпляр {@link PhoneService#getPhoneById(Long)} (Long)} (Long)}</br>
 * <br>Получить все экземпляры {@link PhoneService#getAllPhones()} (Long)} (Long)}</br>
 */
@Service
@Slf4j
public class PhoneService {

    private PhoneRepository phoneRepository;

    @Autowired
    public void setPhoneRepository(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public void deletePhoneById(Long id) {
        phoneRepository.deleteById(id);
    }

    public Phone getPhoneById(Long id) throws IllegalArgumentException {
        Optional<Phone> phone = phoneRepository.findById(id);

        return phone.orElse(null);
    }

    public Iterable<Phone> getAllPhones() {
        return phoneRepository.findAll();
    }

    public Phone getPhoneByName(String name) {
        Optional<Phone> phone = phoneRepository.findByName(name);

        return phone.orElse(null);
    }

    public void savePhone(Phone phone) {
        phoneRepository.save(phone);
    }

}
