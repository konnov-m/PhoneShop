package com.example.PhoneShop.services;

import com.example.PhoneShop.models.Display;
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
 * <br>Сохранить {@link PhoneService#savePhone(Phone)}</br>
 * <br>Получить по {@link Display#getTypeMatrix()}. Функция {@link PhoneService#getByTypeMatrix(String)}</br>
 * <br>Получить список по {@link Display#getRate()}. Функция {@link PhoneService#getByDisplayRate}</br>
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
        log.info("getPhoneById() with parameter id = " + id);
        Optional<Phone> phone = phoneRepository.findById(id);

        return phone.orElse(null);
    }

    public Iterable<Phone> getAllPhones() {
        return phoneRepository.findAll();
    }

    public Phone getPhoneByName(String name) {
        log.info("getPhoneByName() with parameter name = " + name);
        Optional<Phone> phone = phoneRepository.findByName(name);

        return phone.orElse(null);
    }

    public void savePhone(Phone phone) {
        log.info("savePhone() with parameter name = " + phone);
        phoneRepository.save(phone);
    }

    public Iterable<Phone> getByTypeMatrix(String typeMatrix) {
        log.info("getByTypeMatrix() with parameter typeMatrix = " + typeMatrix);
        return phoneRepository.findByDisplay_TypeMatrix(typeMatrix);
    }

    /**
     * Метод для сортировки списка телефонов по компании
     * @param phones список телефонов, который нужно отсортировать
     * @param title название компании, телефоны которой нужно оставить.
     * @return Iterable
     */
    public Iterable<Phone> getByCompanyTitle(Iterable<Phone> phones, String title) {
        log.info("getByCompanyTitle() with parameter title = " + title);
        log.debug("getByCompanyTitle() with parameter phones = " + phones);
        List<Phone> needPhones = new ArrayList<>();

        for (Phone phone: phones) {
            if (phone.getCompany().getTitle().equals(title)) {
                needPhones.add(phone);
            }
        }

        return needPhones;
    }

    /**
     * Метод для сортировки списка телефонов по компании
     * @param phones список телефонов, который нужно отсортировать
     * @param rate Частато экрана, которая должна быть у телефонов.
     * @return Iterable&lt;{@link Phone}&gt;
     */
    public Iterable<Phone> getByDisplayRate(Iterable<Phone> phones, int rate) {
        log.info("getByDisplayRate() with parameter rate = " + rate);
        log.debug("getByDisplayRate() with parameter phones = " + phones);
        List<Phone> needPhones = new ArrayList<>();

        for (Phone phone: phones) {
            if (phone.getDisplay().getRate() == rate) {
                needPhones.add(phone);
            }
        }

        return needPhones;
    }

}
