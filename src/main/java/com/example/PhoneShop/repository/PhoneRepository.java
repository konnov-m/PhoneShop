package com.example.PhoneShop.repository;

import com.example.PhoneShop.models.Phone;
import org.springframework.data.repository.CrudRepository;

public interface PhoneRepository extends CrudRepository<Phone, Long> {
}
