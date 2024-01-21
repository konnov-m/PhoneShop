package com.example.PhoneShop.services;

import com.example.PhoneShop.models.Phone;
import com.example.PhoneShop.repository.PhoneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>Сервис по работе с {@link Phone}.<p/>
 * <br>Удаление {@link PhoneService#deletePhoneById(Long)} (Long)}</br>
 * <br>Получить экземпляр {@link PhoneService#getPhoneById(Long)}</br>
 * <br>Получить все экземпляры {@link PhoneService#getAllPhones()}</br>
 * <br>Получить экземпляр по {@link Phone#getName()}. Функция {@link PhoneService#getPhoneByName(String)}</br>
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

    public Iterable<Phone> getByTypeMatrix(String typeMatrix) {
        return phoneRepository.findByDisplay_TypeMatrix(typeMatrix);
    }

    public Iterable<Phone> getByCompanyTitle(String title) {
        return phoneRepository.findByCompany_Title(title);
    }

    public Iterable<Phone> getByCompanyTitle(Iterable<Phone> phones, String title) {
        List<Phone> needPhones = new ArrayList<>();

        for (Phone phone: phones) {
            if (phone.getCompany().getTitle().equals(title)) {
                needPhones.add(phone);
            }
        }

        return needPhones;
    }

}
